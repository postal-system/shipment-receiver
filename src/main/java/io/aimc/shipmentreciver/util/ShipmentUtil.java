package io.aimc.shipmentreciver.util;

import io.aimc.shipmentreciver.dto.PersonDto;

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
