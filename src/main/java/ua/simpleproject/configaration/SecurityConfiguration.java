package ua.simpleproject.configaration;

import ua.simpleproject.command.CommandEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SecurityConfiguration {
    private static final SecurityConfiguration config = new SecurityConfiguration();
    //roles
    public static final String ALL = "ALL";
    public static final String AUTH = "AUTH";

    private Map<String,String> grant = new HashMap<>();

    public static SecurityConfiguration getInstance(){
        return config;
    }

    private SecurityConfiguration() {
        grant.put(CommandEnum.LOGIN.toString(),"ALL");
        grant.put(CommandEnum.LOGOUT.toString(),"AUTH");
        grant.put(CommandEnum.OPENCHECK.toString(),"AUTH");
        grant.put(CommandEnum.ADDPRODUCT.toString(),"AUTH");
        grant.put(CommandEnum.CLOSECHECK.toString(),"AUTH");
        grant.put(CommandEnum.CREATEPRODUCT.toString(),"AUTH");
        grant.put(CommandEnum.CLOSECHECK.toString(),"AUTH");
        grant.put(CommandEnum.GETPRODUCTSTOCK.toString(),"AUTH");
        grant.put(CommandEnum.OPENCURRENTCHECK.toString(),"AUTH");
        grant.put(CommandEnum.OUTX.toString(),"AUTH");
        grant.put(CommandEnum.OUTZ.toString(),"AUTH");
        grant.put(CommandEnum.PAGINATIONSTOCK.toString(),"AUTH");
        grant.put(CommandEnum.REGISTRATION.toString(),"ALL");
        grant.put("/","ALL");
    }

    public String security(String command){
        return grant.get(command);
    }

    public Set<String> getEndPoints(){
        return grant.keySet();
    }
}
