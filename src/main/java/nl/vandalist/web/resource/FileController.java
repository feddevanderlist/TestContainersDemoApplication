package nl.vandalist.web.resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//    @PostMapping(
//            value = "",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<Object> processZip(MultipartHttpServletRequest multipartHttpServletRequest) {
//        multipartHttpServletRequest.getFileMap().forEach((name, file) -> {
//            if (file != null && !file.isEmpty()) {
//                if (!file.getContentType().isEmpty() && !file.getContentType().equals("application/zip")) {
//                    try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
//                         ExecutorService executor = Executors.newFixedThreadPool(5)) {
//
//
//                        ZipEntry entry;
//
//                        while ((entry = zipInputStream.getNextEntry()) != null) {
//                            if (!entry.isDirectory()) {
//                                ZipEntry finalEntry = entry;
//                                executor.execute(() -> processZipEntry(finalEntry, zipInputStream));
//                            }
//                            zipInputStream.closeEntry();
//                        }
//
//
//                    } catch (IOException e) {
//                        Logger.getAnonymousLogger().severe(e.getMessage());
////                        return ResponseEntity.badRequest().body("File is not a zip file");
//                    }
//                }
//            }
////            return ResponseEntity.ok("processing successful for: " + file.getName());
//        });
//
//
//        return ResponseEntity.badRequest().
//
//                body("something went wrong");
//
//    }
//
//    private void processZipEntry(ZipEntry entry, ZipInputStream zipInputStream) {
//        // Implement your logic to process each entry here
//        // You can read from zipInputStream and process the entry's content
//        // Example: Save the entry content to a file, perform some computation, etc.
//
//        final String outputFilePath = "src/main/resources" + File.separator + entry.getName();
//
//        try {
//            java.nio.file.Files.copy(zipInputStream, Path.of(outputFilePath));
//        } catch (IOException e) {
//            Logger.getAnonymousLogger().severe(e.getMessage());
//        }
//
//    }
//}