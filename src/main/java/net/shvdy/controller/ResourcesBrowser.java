/**
 * ResourceBrowser
 * <p>
 * version 2
 * <p>
 * 13.02.2020
 * <p>
 * Copyright(r) shvdy
 */
package net.shvdy.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Scans jar /resources folder on available ".properties" files,
 * saves all localisations to {@link List}<{@link Locale}> and regular expressions as {@link List}<{@link ResourceBundle}>
 *
 * @see ResourceBundle
 */
class ResourcesBrowser {
    private List<Locale> availableLocales = new ArrayList<>();
    private List<ResourceBundle> availableRegex = new ArrayList<>();

    public List<Locale> getAvailableLocales() {
        return availableLocales;
    }

    public List<ResourceBundle> getAvailableRegex() {
        return availableRegex;
    }

    /**
     * Scans jar /resources folder on available ".properties" files,
     * saves all localisations to {@link List}<{@link Locale}> and regular expressions as {@link List}<{@link ResourceBundle}></{@link>
     *
     * @see ResourceBundle
     */
    void loadFromJarResources() throws IOException, URISyntaxException {
        CodeSource src = ResourcesBrowser.class.getProtectionDomain().getCodeSource();
        ZipInputStream jar = null;
        ZipEntry ze;

        if (src != null) {
            URI jarUri = new URI(src.getLocation().toString());
            URL jarUrl = jarUri.toURL();
            jar = new ZipInputStream(jarUrl.openStream());
        }

        while ((ze = jar.getNextEntry()) != null) {
            String entryName = ze.getName();
            if (entryName.startsWith("controller/locale_") && entryName.endsWith(".properties")) {
                availableLocales.add(new Locale(entryName
                        .replaceFirst("controller/locale_", "")
                        .replaceFirst(".properties", "")));
            } else if (entryName.startsWith("controller/regex_") && entryName.endsWith(".properties")) {
                availableRegex.add(ResourceBundle.getBundle("controller/regex",
                        new Locale(entryName
                                .replaceFirst("controller/regex_", "")
                                .replaceFirst(".properties", ""))));
            }
        }
    }
}
