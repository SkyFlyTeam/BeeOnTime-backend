package com.fatec.ms_usuario.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.ms_usuario.entidade.Empresa;
import com.fatec.ms_usuario.repositorio.EmpresaRepositorio;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*")
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

    @GetMapping("/{empCod}")
    public ResponseEntity<Empresa> obterEmpresaPorId(@PathVariable Long empCod) {
        return repositorio.findById(empCod)
                .map(empresa -> new ResponseEntity<>(empresa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{empCod}")
    public ResponseEntity<Empresa> editarEmpresa(@PathVariable Long empCod, @RequestBody Empresa empresaAtualizada) {
        return repositorio.findById(empCod)
                .map(empresa -> {
                    empresa.setEmpNome(empresaAtualizada.getEmpNome());
                    empresa.setEmpCnpj(empresaAtualizada.getEmpCnpj());
                    empresa.setEmpRazaoSocial(empresaAtualizada.getEmpRazaoSocial());
                    empresa.setEmpCep(empresaAtualizada.getEmpCep());
                    empresa.setEmpCidade(empresaAtualizada.getEmpCidade());
                    empresa.setEmpEstado(empresaAtualizada.getEmpEstado());
                    empresa.setEmpEndereco(empresaAtualizada.getEmpEndereco());
                    Empresa updatedEmpresa = repositorio.save(empresa);
                    return new ResponseEntity<>(updatedEmpresa, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{empCod}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long empCod) {
        if (repositorio.existsById(empCod)) {
            repositorio.deleteById(empCod);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
