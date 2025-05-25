package com.ms.espelho_ponto.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ms.espelho_ponto.dto.HorasTrabalhadasDTO;
import com.ms.espelho_ponto.dto.PontoDTO;
import com.ms.espelho_ponto.model.EspelhoPonto;

@Service
public class PdfService {

    private final WkhtmltopdfService wkhtmltopdfService = new WkhtmltopdfService();

    public byte[] generateUserPointsPdf(String empNome, String empCNPJ, String username, String nrRegistro, String usuarioCpf,
     String cargo, String setor, String bancoHoras, String horasTrabalhadas, String monthName, 
     List<HorasTrabalhadasDTO> histPontos, List<PontoDTO> pontos, EspelhoPonto espelhoPontoRelacionado) throws IOException, InterruptedException {
        // Load your HTML template, replace placeholders as before
        String htmlContent = readHtmlFromFile("src/main/resources/templates/user_points_template.html");
        htmlContent = htmlContent.replace("${empNome}", empNome);
        htmlContent = htmlContent.replace("${empCNPJ}", empCNPJ);
        htmlContent = htmlContent.replace("${username}", username);
        if (nrRegistro != null){
            htmlContent = htmlContent.replace("${nrRegistro}", nrRegistro);
        }
        else {
            htmlContent = htmlContent.replace("${nrRegistro}", "---");
        }
        htmlContent = htmlContent.replace("${cargo}", cargo);
        htmlContent = htmlContent.replace("${setor}", setor);
        htmlContent = htmlContent.replace("${bancoHoras}", bancoHoras);
        htmlContent = htmlContent.replace("${horasTrabalhadas}", horasTrabalhadas);
        htmlContent = htmlContent.replace("${monthName}", monthName);

        String tabelaPontos = "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (HorasTrabalhadasDTO histPontosTrabalhados : histPontos) {
            if (histPontosTrabalhados.getData() != null) {
                // Process the date if needed (in case it's a LocalDate already)
                System.out.println("Date is: " + histPontosTrabalhados.getData());
        
                // Check if it's a weekend or weekday
                DayOfWeek dayOfWeek = histPontosTrabalhados.getData().getDayOfWeek();
                if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                    System.out.println("Weekend: " + histPontosTrabalhados.getData());
                    tabelaPontos += """
                        <tr style="text-align: center;">
                            <td style="background-color: #CBD5E1;">""";
                    tabelaPontos += histPontosTrabalhados.getData().format(formatter);
                    tabelaPontos += """ 
                            </td>
                            <td style="background-color: #CBD5E1;">""";
                    tabelaPontos += dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, new Locale("pt", "BR"));
                    tabelaPontos += """
                        </td>
                            <td colspan="6" style="background-color: #CBD5E1;"></td>
                        </tr>
                            """;
                } else {
                    System.out.println("Weekday: " + histPontosTrabalhados.getData());

                    List<PontoDTO> filteredponto = pontos.stream()
                        .filter(ponto -> ponto.getData().equals(histPontosTrabalhados.getData().toString()))
                        .collect(Collectors.toList());

                    tabelaPontos += """
                    <tr style="text-align: center;">
                    """;
                    tabelaPontos += "<td>" + histPontosTrabalhados.getData().format(formatter) + "</td>";
                    tabelaPontos += "<td>" + dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, new Locale("pt", "BR")) + "</td>";
                    if (!filteredponto.isEmpty()){
                        if (filteredponto.get(0).getPontos().size() == 4){
                            tabelaPontos += "<td>" + filteredponto.get(0).getPontos().get(0).getHorarioPonto() + "</td>";
                            tabelaPontos += "<td>" + filteredponto.get(0).getPontos().get(1).getHorarioPonto() + "</td>";
                            tabelaPontos += "<td>" + filteredponto.get(0).getPontos().get(2).getHorarioPonto() + "</td>";
                            tabelaPontos += "<td>" + filteredponto.get(0).getPontos().get(3).getHorarioPonto() + "</td>";
                        }
                        else {
                            tabelaPontos += "<td></td> <td></td> <td></td> <td></td>";
                        }
                    }
                    else {
                        tabelaPontos += "<td></td> <td></td> <td></td> <td></td>";
                    }
                    tabelaPontos += """
                    <td style="background-color: #FFCB50;">
                    """;
                    tabelaPontos += histPontosTrabalhados.getHorasTotal() + "</td>";
                    tabelaPontos += """
                    <td style="background-color: #FFCB50;">
                    """;
                    tabelaPontos += histPontosTrabalhados.getDesconto() + "</td> </tr>";
                }
            }
        }

        htmlContent = htmlContent.replace("${pontos}", tabelaPontos);

        String assinaturaInfo = "";

        if (espelhoPontoRelacionado != null) {
            if (espelhoPontoRelacionado.isEspelhoPontoAssinado()) {
                assinaturaInfo += "<h3>" + username + "</h3>";
                assinaturaInfo += """
                        <p style="color: 0F172A;">
                        """;
                assinaturaInfo += "CPF: " + usuarioCpf + "</p>";
                assinaturaInfo += """
                        <p style="color: 0F172A;">
                        """;
                // Get the Date object and convert to LocalDateTime to extract both date and time
                Date espelhoPontoDataAssinatura = espelhoPontoRelacionado.getEspelhoPontoDataAssinatura();
    
                // Convert to LocalDateTime if it's a Date object
                LocalDateTime localDateTime = espelhoPontoDataAssinatura.toInstant()
                                   .atZone(ZoneId.systemDefault())
                                   .toLocalDateTime();

                // Format the date and time separately
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                String formattedDate = localDateTime.format(dateFormatter);
                String formattedTime = localDateTime.format(timeFormatter);
        
                assinaturaInfo += "Assinou em " +  formattedDate + " às " + formattedTime + "</p>";
            }
        }

        htmlContent = htmlContent.replace("${assinatura}", assinaturaInfo);

        // Generate PDF bytes via wkhtmltopdf CLI
        return wkhtmltopdfService.generatePdfFromHtml(htmlContent);
    }

    private String readHtmlFromFile(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }
}


