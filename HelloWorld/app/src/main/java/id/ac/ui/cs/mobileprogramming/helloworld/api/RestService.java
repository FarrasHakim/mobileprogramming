package id.ac.ui.cs.mobileprogramming.helloworld.api;

import id.ac.ui.cs.mobileprogramming.helloworld.model.PhotoData;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface RestService {
    @GET("/photos")
    Call<List<PhotoData>> getAllPhotos();
}
