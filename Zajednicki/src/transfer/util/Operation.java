/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Ana
 */
public interface Operation {

    public static final int ADD_CLIENT = 0;
    public static final int DELETE_CLIENT = 1;
    public static final int UPDATE_CLIENT = 2;
    public static final int GET_ALL_CLIENT = 3;

    public static final int ADD_ACCOUNT = 4;
    public static final int DELETE_ACCOUNT = 5;
    public static final int UPDATE_ACCOUNT = 6;
    public static final int GET_ALL_ACCOUNT = 7;

    public static final int LOGIN = 8;
    public static final int GET_ALL_ADMINISTRATOR = 9;

}
