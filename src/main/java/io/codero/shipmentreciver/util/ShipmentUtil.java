package io.codero.shipmentreciver.util;

import io.codero.shipmentreciver.dto.PersonDto;

public class ShipmentUtil {
    public static String getConcatName(PersonDto personDto) {
        return personDto.getFirstName()
                .concat(" ").concat(personDto.getLastName())
                .concat(" ").concat(getNonNullString(personDto.getPatronymic())).trim();
    }

    private static String getNonNullString(String str) {
        return str != null ? str : "";
    }
}
