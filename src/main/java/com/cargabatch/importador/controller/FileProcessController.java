package com.cargabatch.importador.controller;
import com.cargabatch.importador.DTO.BitacoraDTO;
import com.cargabatch.importador.DTO.FileDTO;
import com.cargabatch.importador.DTO.ProcessDTO;
import com.cargabatch.importador.entitys.FileEntity;
import com.cargabatch.importador.exceptions.UsuarioServiceException;
import com.cargabatch.importador.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileProcessController {

    private final ReaderService readerService;
    private final FileProcessService fileProcessService;
    private final BitacoraService bitacoraService;
    private final SucursalesService sucursalesService;
    private final UsuarioService   usuarioService;

    @Autowired
    public FileProcessController(ReaderService readerService, FileProcessService fileProcessService, BitacoraService bitacoraService, SucursalesService sucursalesService, UsuarioService usuarioService) {
        this.readerService = readerService;
        this.fileProcessService = fileProcessService;
        this.bitacoraService = bitacoraService;
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
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Crear y guardar FileEntity
            FileEntity fileEntity = new FileEntity();
            fileEntity.setNombre(file.getOriginalFilename());
            fileEntity.setFileSize(file.getSize());
            fileEntity.setUploadDate(new Date());
            fileProcessService.saveFileEntity(fileEntity);

            // Procesar archivo
            String response = fileProcessService.validateAndProcessFile(file);
            return ResponseEntity.ok(response);
        } catch (IOException | UsuarioServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
        }
    }

    @GetMapping("/bitacora")
    public ResponseEntity<List<BitacoraDTO>> getBitacora() {
        List<BitacoraDTO> bitacoras = bitacoraService.getAllBitacoras();
        return ResponseEntity.ok(bitacoras);
    }
}