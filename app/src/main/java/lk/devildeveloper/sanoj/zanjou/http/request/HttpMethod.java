package lk.devildeveloper.sanoj.zanjou.http.request;



import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static lk.devildeveloper.sanoj.zanjou.http.request.Request.DELETE;
import static lk.devildeveloper.sanoj.zanjou.http.request.Request.GET;
import static lk.devildeveloper.sanoj.zanjou.http.request.Request.OPTIONS;
import static lk.devildeveloper.sanoj.zanjou.http.request.Request.POST;
import static lk.devildeveloper.sanoj.zanjou.http.request.Request.PUT;
import static lk.devildeveloper.sanoj.zanjou.http.request.Request.TRACE;

/**
 * Created by ander on 9/12/17.
 */

@StringDef({GET, POST, PUT, DELETE, TRACE, OPTIONS})
@Retention(RetentionPolicy.SOURCE)
public @interface HttpMethod {
}
