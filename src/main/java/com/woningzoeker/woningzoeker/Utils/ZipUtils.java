package com.woningzoeker.woningzoeker.Utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    /**
     * Maakt een ZIP-bestand van een lijst van bestanden (Resources).
     * @param files de lijst van resources die gecomprimeerd moeten worden.
     * @return ByteArrayResource die de ZIP-bestanden bevat.
     * @throws IOException als er een fout optreedt bij het lezen of schrijven van de bestanden.
     */
    public static ByteArrayResource zipFiles(List<Resource> files) throws IOException {
        // In geheugen ByteArrayOutputStream maken
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // ZIP-bestand maken in de outputstream
        try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
            for (Resource file : files) {
                // Elke resource wordt toegevoegd als een nieuwe entry in de zip
                try (InputStream is = file.getInputStream()) {
                    zipOut.putNextEntry(new ZipEntry(file.getFilename())); // Bestandsnaam in de zip
                    is.transferTo(zipOut);  // Schrijf de inhoud van het bestand naar de zip
                    zipOut.closeEntry();    // Sluit de entry
                }
            }
        }

        // Retourneer de byte-array als een resource
        return new ByteArrayResource(baos.toByteArray());
    }
}
