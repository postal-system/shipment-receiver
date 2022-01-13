package io.aimc.shipmentreciver.util;

import io.aimc.shipmentreciver.dto.PersonDto;

public class ShipmentUtil {
    public static String getConcatName(PersonDto personDto) {
        return personDto.getFirstName()
                .concat(" ").concat(personDto.getLastName())
                .concat(" ").concat(personDto.getPatronymic());
    }
}
