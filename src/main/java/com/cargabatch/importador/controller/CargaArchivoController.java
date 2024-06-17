package com.cargabatch.importador.controller;
import com.cargabatch.importador.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;


@RestController
public class CargaArchivoController {

    private final UsuarioService usuarioService;
    private final String rutaArchivo;

    @Autowired
    public CargaArchivoController(UsuarioService usuarioService, @Value("${ruta.yalgo}") String rutaArchivo) {
        this.usuarioService = usuarioService;
        this.rutaArchivo = rutaArchivo;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) throws IOException {

        // Recibe archivo Excel
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha seleccionado ningún archivo");
        }

        // Comprueba si la ruta está en properties y es válida
        if (rutaArchivo == null || rutaArchivo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ruta del archivo no está configurada en application.properties");
        }
        File ruta = new File(rutaArchivo);
        if (!ruta.exists() || !ruta.isDirectory()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ruta del archivo no es válida");
        }

        // Llama al servicio para validar y guardar usuarios
        String result = usuarioService.validarYGuardarUsuarios(file);

        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
