package com.api.empresa.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.empresa.entidade.Empresa;
import com.api.empresa.repositorio.EmpresaRepositorio;

@RestController
@RequestMapping("/empresa") // Base URL para os endpoints da empresa
public class EmpresaControle {

    @Autowired
    private EmpresaRepositorio repositorio;

    @GetMapping
    public ResponseEntity<List<Empresa>> obterTodasEmpresas() {
        List<Empresa> empresas = repositorio.findAll();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody Empresa empresa) {
        Empresa savedEmpresa = repositorio.save(empresa);
        return new ResponseEntity<>(savedEmpresa, HttpStatus.CREATED);
    }

    @GetMapping("/{emp_cod}")
    public ResponseEntity<Empresa> obterEmpresaPorId(@PathVariable Long emp_cod) {
        return repositorio.findById(emp_cod)
                .map(empresa -> new ResponseEntity<>(empresa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{emp_cod}")
    public ResponseEntity<Empresa> editarEmpresa(@PathVariable Long emp_cod, @RequestBody Empresa empresaAtualizada) {
        return repositorio.findById(emp_cod)
                .map(empresa -> {
                    empresa.setEmp_nome(empresaAtualizada.getEmp_nome());
                    empresa.setEmp_cnpj(empresaAtualizada.getEmp_cnpj());
                    empresa.setEmp_razaoSocial(empresaAtualizada.getEmp_razaoSocial());
                    empresa.setEmp_cep(empresaAtualizada.getEmp_cep());
                    empresa.setEmp_cidade(empresaAtualizada.getEmp_cidade());
                    empresa.setEmp_estado(empresaAtualizada.getEmp_estado());
                    empresa.setEmp_endereco(empresaAtualizada.getEmp_endereco());
                    Empresa updatedEmpresa = repositorio.save(empresa);
                    return new ResponseEntity<>(updatedEmpresa, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{emp_cod}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long emp_cod) {
        if (repositorio.existsById(emp_cod)) {
            repositorio.deleteById(emp_cod);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}