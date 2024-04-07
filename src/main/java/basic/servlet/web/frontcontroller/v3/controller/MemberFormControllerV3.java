package basic.servlet.web.frontcontroller.v3.controller;

import basic.servlet.web.frontcontroller.ModelView;
import basic.servlet.web.frontcontroller.MyView;
import basic.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
