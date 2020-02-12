package net.shvdy.controller;

public interface Regex {
    String surnameEng = "[A-Z][A-Za-z ] {1,20}";
    String surnameUa = "[А-ЯІЇЄҐ^ЫЭЪЁ][А-Яа-яІЇЄҐіїєґ[']^ЫЭЯЁыэяё] {1,29}[а-яіїєґ^ЫЭЯЁыэяё]";
    String nickEng = "[A-Z][A-Za-z0-9] {3,15}";
    String nickUa = "[A-Z][A-Za-z0-9] {3,15}";
}
