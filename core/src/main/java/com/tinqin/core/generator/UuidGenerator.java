package com.tinqin.core.generator;

import org.apache.commons.lang3.RandomStringUtils;

public class UuidGenerator {

    public String generate(){
        return RandomStringUtils.randomAlphanumeric(8)
                + "-" + RandomStringUtils.randomAlphanumeric(4)
                + "-" + RandomStringUtils.randomAlphanumeric(4)
                + "-" + RandomStringUtils.randomAlphanumeric(4)
                + "-" + RandomStringUtils.randomAlphanumeric(12);
    }

}
