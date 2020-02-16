package br.com.boraviajar.trailmanagement.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Log4j2
@UtilityClass
public class FieldDictionary {

    private static final String PATTERN = "\\W[0-9]\\W";

    private static final Map<String, String> MAP = map("fields.json");

    public static String translate(@NonNull final String field) {

        return Optional
                .ofNullable(field)
                .filter(StringUtils::isNotEmpty)
                .filter(MAP::containsKey)
                .flatMap(e -> Optional.of(MAP.get(e)))
                .orElseGet(() -> {
                    final String replaced = field.replaceAll(PATTERN, StringUtils.EMPTY);
                    return MAP.containsKey(replaced) ? MAP.get(replaced) : field;
                });
    }

    private static Map<String, String> map(final String resource) {

        try (final InputStream is = new ClassPathResource(resource).getInputStream()) {
            final byte[] bytes = FileCopyUtils.copyToByteArray(is);
            return Collections.unmodifiableMap(new ObjectMapper().readValue(bytes, new TypeReference<Map<String, String>>() {}));
        } catch (Exception e) {
            log.error("Falha ao ler arquivo de dicionário de campos", e);
            throw new IllegalStateException("Falha ao ler arquivo de dicionário de campos", e);
        }
    }
}
