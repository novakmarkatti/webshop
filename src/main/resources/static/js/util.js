function getUserData() {
    return {
        id: $("#userId").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        username: $("#username").val(),
        billingAddress: {
            id: $("#billingAddressId").val(),
            zipCode: $("#billingAddressZipCode").val(),
            city: $("#billingAddressCity").val(),
            street: $("#billingAddressStreet").val(),
            houseNumber: $("#billingAddressHouseNumber").val(),
            stairs: $("#billingAddressStairs").val(),
            flat: $("#billingAddressFlat").val(),
            door: $("#billingAddressDoor").val()
        },
        deliveryAddress: {
            id: $("#deliveryAddressId").val(),
            zipCode: $("#deliveryAddressZipCode").val(),
            city: $("#deliveryAddressCity").val(),
            street: $("#deliveryAddressStreet").val(),
            houseNumber: $("#deliveryAddressHouseNumber").val(),
            stairs: $("#deliveryAddressStairs").val(),
            flat: $("#deliveryAddressFlat").val(),
            door: $("#deliveryAddressDoor").val()
        }
    };
}