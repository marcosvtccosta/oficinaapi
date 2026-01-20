package com.oficina.api.domain.entity;

public abstract class PlacaRegexProvider {
    private PlacaRegexProvider() {}
    public static final String REGEX_MERCOSUL = "^[A-Z]{3}\\d[A-Z]\\d{2}$";
    public static final String REGEX_ANTIGA = "^[A-Z]{3}\\d{4}$";
}
