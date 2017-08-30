

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import io.swagger.annotations.ApiParam;
import io.swagger.api.ConfigApi;
import io.swagger.freemarker.TempleteEngine;
import io.swagger.model.*;
import io.swagger.process.HaproxyProcess;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.Map;

public class JsonTest {

    public static void main(String[] args){

        Gson gson = new Gson();

        Config config = new Config();

        Global global = new Global();

        global.setMode("http");
        global.setName("demon");
        global.setMaxconn(1000l);
        Options options = new Options();

        config.setGlobal(global);

        System.out.println(config);


        System.out.println(gson.toJson(config));
    }



}
