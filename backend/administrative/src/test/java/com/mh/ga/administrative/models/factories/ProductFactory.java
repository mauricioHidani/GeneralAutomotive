package com.mh.ga.administrative.models.factories;

import com.mh.ga.administrative.models.entities.Product;
import com.mh.ga.administrative.models.transfers.ProductRequest;
import com.mh.ga.administrative.models.transfers.ProductResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductFactory {
    public static Product createEntityWithID(UUID id) {
        return new Product(
                id,
                "Separador de Óleo Anti-chama de Motor VW 030103464A",
                "Separador de Óleo Anti-chama de Motor original para seu VW, o código 030-103-464-A aplica em Parati Polo Kombi Voyage Fox Saveiro SpaceFox Gol.",
                1200d,
                7.22d,
                10d,
                new BigDecimal("151.90")
        );
    }

    public static Product createEntityWithoutID() {
        return new Product(
                null,
                "Separador de Óleo Anti-chama de Motor VW 030103464A",
                "Separador de Óleo Anti-chama de Motor original para seu VW, o código 030-103-464-A aplica em Parati Polo Kombi Voyage Fox Saveiro SpaceFox Gol.",
                1200d,
                7.22d,
                10d,
                new BigDecimal("151.90")
        );
    }

    public static ProductRequest createRequest() {
        return new ProductRequest(
                null,
                "Separador de Óleo Anti-chama de Motor VW 030103464A",
                "Separador de Óleo Anti-chama de Motor original para seu VW, o código 030-103-464-A aplica em Parati Polo Kombi Voyage Fox Saveiro SpaceFox Gol.",
                1200d,
                7.22d,
                10d,
                new BigDecimal("151.90")
        );
    }

    public static ProductResponse createResponseWithID(UUID id) {
        return new ProductResponse(
                id.toString(),
                "Separador de Óleo Anti-chama de Motor VW 030103464A",
                "Separador de Óleo Anti-chama de Motor original para seu VW, o código 030-103-464-A aplica em Parati Polo Kombi Voyage Fox Saveiro SpaceFox Gol.",
                1200d,
                7.22d,
                10d,
                new BigDecimal("151.90")
        );
    }

    public static ProductResponse createResponseWithoutID() {
        return new ProductResponse(
                null,
                "Separador de Óleo Anti-chama de Motor VW 030103464A",
                "Separador de Óleo Anti-chama de Motor original para seu VW, o código 030-103-464-A aplica em Parati Polo Kombi Voyage Fox Saveiro SpaceFox Gol.",
                1200d,
                7.22d,
                10d,
                new BigDecimal("151.90")
        );
    }
}
