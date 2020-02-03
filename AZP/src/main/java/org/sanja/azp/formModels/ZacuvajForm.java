/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sanja.azp.formModels;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Sanja
 */
@Getter
@Setter
public class ZacuvajForm {

    private Long id;
    private String komentar;
    private String simptomi;
    private String[] proceduri;

}