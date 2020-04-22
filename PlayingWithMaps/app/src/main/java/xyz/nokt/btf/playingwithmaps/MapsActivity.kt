package xyz.nokt.btf.playingwithmaps

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.android.PolyUtil
import com.google.maps.model.DirectionsResult
import com.google.maps.model.TravelMode
import kotlinx.android.synthetic.main.activity_maps.*
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkForPermission()
    }

    var LOCCODE = 232
    fun checkForPermission()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(ActivityCompat
            .checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),LOCCODE)
                return
            }
        }

        getUserLocation()
    }

    @SuppressLint("MissingPermission")
    fun getUserLocation()
    {
        Toast.makeText(this, "Location services enabled", Toast.LENGTH_LONG).show()

        var myLocation = MyLocationListener()

        var locmanager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)
    }
    fun getLatLngFromAddres(context:Context, mAddress:String):LatLng
    {
        var geocoder = Geocoder(context)
        var addresses:List<Address>

        var addressPos:LatLng = LatLng(0.0,0.0)
        try {
            addresses = geocoder.getFromLocationName(mAddress,1)
            if (addresses == null)
            {
                Toast.makeText(context, "Enter a valid address", Toast.LENGTH_LONG).show();
            }
            var loc:Address = addresses.get(0)
            addressPos = LatLng(loc.latitude, loc.longitude)
        }catch (ex:Exception)
        {
            ex.printStackTrace()
        }
        return addressPos
    }

    fun getGeoContext():GeoApiContext
    {
        val context: GeoApiContext = GeoApiContext.Builder()
            .apiKey(getString(R.string.google_maps_key))
            .queryRateLimit(3)
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .build()

        return context
    }

    private fun getDirectionsResult(startLoc:String, destination:String, mode: TravelMode): DirectionsResult
    {
        var dateTime = DateTime()

        return DirectionsApi.newRequest(getGeoContext())
            .mode(mode)
            .origin(startLoc)
            .destination(destination)
            .departureTime(dateTime)
            .await()
    }
    fun plotRoute(destination: String)
    {
        var result:DirectionsResult=getDirectionsResult("Road C2 FHA, Nyanya-Karu, Abuja",destination, TravelMode.DRIVING)
        var strAddress:String = "Wuse Zone 2, Abuja"
        var address:LatLng = getLatLngFromAddres(this, strAddress)

        var startLoc = LatLng(_location!!.latitude, _location!!.longitude)
        Log.i("StartLoc", startLoc.toString())
        Log.i("End", address.toString())

        var marker:Marker = mMap.addMarker(MarkerOptions().position(address)
            .title(strAddress)
            .snippet(moveInfo(result))
        )

        marker.showInfoWindow()

        addPoly(result, mMap)
    }

    fun searchAddress(view:View)
    {
        if (tvDestSearch.text.toString() != null) {
            plotRoute(tvDestSearch.text.toString())
        }
    }

    fun moveInfo(result: DirectionsResult):String
    {
        var strResult:String = "Distance: "+result!!.routes[0].legs[0].distance.humanReadable+
                " Time: "+result!!.routes[0].legs[0].duration.humanReadable
        Log.i("MoveDetails", strResult)
        return strResult
    }

    fun addPoly(result:DirectionsResult, mMap:GoogleMap)
    {
        var nPath:List<LatLng> = PolyUtil.decode(result.routes[0].overviewPolyline.encodedPath)
        //PolylineEncoding.decode(
        mMap.addPolyline(PolylineOptions().color(Color.BLUE).addAll(nPath))
    }
    override fun onRequestPermissionsResult(requestCode: Int,  permissions: Array<out String>,grantResults: IntArray) {
        when(requestCode)
        {
            LOCCODE->{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    getUserLocation()
                }else{
                    Toast.makeText(this, "We can't Find You", Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //val sydney = LatLng(-34.0, 151.0)
        //plotRoute()
    }

    var _location:Location?=null
    inner class MyLocationListener:LocationListener {
        constructor() {
            _location = Location("Start")
            _location!!.longitude = 0.0
            _location!!.latitude = 0.0
        }

        override fun onLocationChanged(location: Location?) {
            _location = location

            var marker: Marker? = null
            val youloc = LatLng(_location!!.latitude, _location!!.longitude)
            // Add a marker in Sydney and move the camera
            mMap.uiSettings.isZoomControlsEnabled = true
            //mMap.setMinZoomPreference(17f)
            mMap.clear()
            //marker?.remove()
            var markerOptions: MarkerOptions = MarkerOptions().position(youloc)
                .title("You are outside")
                .snippet("Shouldn't you be in doors?")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.noktpushpin))

            marker = mMap.addMarker(markerOptions)
            marker.showInfoWindow()


            mMap.addCircle(
                CircleOptions()
                    .center(youloc)
                    .radius(50.0)
                    .fillColor(Color.CYAN)
                    .strokeColor(Color.BLUE)
            )

            //mMap.setMinZoomPreference(17f)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(youloc))

            if (tvDestSearch.text.toString() != null || tvDestSearch.text.toString() !="") {
                plotRoute(tvDestSearch.text.toString())
            }else{
                plotRoute("Wuse Zone 2")
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}
