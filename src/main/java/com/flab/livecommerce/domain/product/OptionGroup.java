package com.flab.livecommerce.domain.product;

import java.util.ArrayList;
import java.util.List;

public class OptionGroup {

    private long id;
    private String name;
    //기본 옵션 여부
    private boolean basic;
    //배타 선택 여부
    private boolean exclusive;
    //최소 선택 개수
    private int minimumChoice;
    //최대 선택 개수
    private int maximumChoice;
    private List<Option> options = new ArrayList<>();
}
