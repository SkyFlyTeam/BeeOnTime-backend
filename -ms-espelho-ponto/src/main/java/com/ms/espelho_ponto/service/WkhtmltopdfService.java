package com.ms.espelho_ponto.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class WkhtmltopdfService {

    // Converts HTML string to PDF bytes using wkhtmltopdf CLI
    public byte[] generatePdfFromHtml(String htmlContent) throws IOException, InterruptedException {
        // Create temp files for HTML input and PDF output
        File htmlFile = File.createTempFile("input", ".html");
        File pdfFile = File.createTempFile("output", ".pdf");
        System.out.println("HTML file path: " + htmlFile.getAbsolutePath());

        // Write HTML content to temp file
        try (FileWriter writer = new FileWriter(htmlFile)) {
            writer.write(htmlContent);
        }

        // Build the command to run wkhtmltopdf
        String command = String.format("wkhtmltopdf " +
            "--enable-local-file-access " +
            "--background " +
            "--dpi 300 " +
            "--zoom 1 " +
            "--page-size A4 " +
            "--margin-top 0mm " +
            "--margin-bottom 0mm " +
            "--margin-left 0mm " +
            "--margin-right 0mm " +
            "%s %s",
            htmlFile.getAbsolutePath(),
            pdfFile.getAbsolutePath()
        );



        // Run the process
        Process process = Runtime.getRuntime().exec(command);

        // Wait for process to finish
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            // Read error stream if failure
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String line;
                StringBuilder errorMsg = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    errorMsg.append(line).append("\n");
                }
                throw new RuntimeException("wkhtmltopdf error: " + errorMsg.toString());
            }
        }

        // Read the generated PDF file bytes
        byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath());

        // Delete temp files
        htmlFile.delete();
        pdfFile.delete();

        return pdfBytes;
    }
}


