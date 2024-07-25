package com.cargabatch.importador.controller;
import com.cargabatch.importador.exceptions.UsuarioServiceException;
import com.cargabatch.importador.services.SucursalesService;
import com.cargabatch.importador.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;


@RestController

public class CargaArchivoController {


    private final String rutaArchivo;
    private final SucursalesService sucursalesService;
    private final UsuarioService usuarioService;


    @Autowired
    public CargaArchivoController(SucursalesService sucursalesService, @Value("${ruta.yalgo}") String rutaArchivo, UsuarioService usuarioService) {

        this.rutaArchivo = rutaArchivo;
        this.sucursalesService = sucursalesService;
        this.usuarioService = usuarioService;
    }


    @PostMapping("/cargainfo")
        public ResponseEntity<Integer> cargaInfo() throws IOException {
        //File file = new File(rutaArchivo+"/LAYOUTS_GYM_05232024.xlsx");
        InputStream inp = new FileInputStream("/LAYOUTS_GYM_0523202411.xlsx");
        var sucursalesList = sucursalesService.procesarArchivoExcel(inp);

        //System.out.println(sucursalesList);
        //usuarioService.procesarArchivoExcel(file);

        return ResponseEntity.status(HttpStatus.OK).body(2);
    }

    @PostMapping("/cargaUsu")
    public ResponseEntity<Integer> CargaUsu() throws IOException {
        //File file = new File(rutaArchivo+"/LAYOUTS_GYM_05232024.xlsx");
        InputStream inp = new FileInputStream("/LAYOUTS_GYM_05232024U.xlsx");
        var usuarioList = usuarioService.procesarArchivoExcel(inp);

        //System.out.println(sucursalesList);
        //usuarioService.procesarArchivoExcel(file);

        return ResponseEntity.status(HttpStatus.OK).body(2);
    }



    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) throws  UsuarioServiceException {

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
        String result = sucursalesService.validarYGuardarSucursales(file);

        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
