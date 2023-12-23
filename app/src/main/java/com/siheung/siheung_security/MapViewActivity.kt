package com.siheung.siheung_security

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.UiSettings
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.widget.CompassView
import com.naver.maps.map.widget.LocationButtonView
import com.naver.maps.map.widget.ScaleBarView
import com.naver.maps.map.widget.ZoomControlView
import java.lang.Exception

// ... (기존에 있는 import문은 그대로 유지합니다)

class MapViewActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    private val PERMISSION_REQUEST_CODE = 100
    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        val fragmentManager: FragmentManager = supportFragmentManager
        var mapFragment = fragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance()
            fragmentManager.beginTransaction().add(R.id.map_fragment, mapFragment).commit()
        }
        mapFragment?.getMapAsync(this)

        locationSource = FusedLocationSource(this, PERMISSION_REQUEST_CODE)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.NoFollow
        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true

        if (hasLocationPermission()) {
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        }
        //정죽 자율방범대
        val Jeonjuk = Marker()
        Jeonjuk.position = LatLng(37.33902277294288, 126.73596673747413)
        Jeonjuk.icon = OverlayImage.fromResource(R.drawable.marker)
        Jeonjuk.map = naverMap
        Jeonjuk.setOnClickListener {
            showDetails("정죽 자율방범대  ${Jeonjuk.position.latitude}, ${Jeonjuk.position.longitude}")
            true
        }
        //배곧 자율방범대
        val Baegot = Marker()
        Baegot.position = LatLng(37.38190542810069, 126.73596673747413)
        Baegot.icon = OverlayImage.fromResource(R.drawable.marker)
        Baegot.map = naverMap
        Baegot.setOnClickListener {
            showDetails("배곧 자율방범대  ${Baegot.position.latitude}, ${Baegot.position.longitude}")
            true
        }
        //월곶 자율방범대
        val Wolgot = Marker()
        Wolgot.position = LatLng(37.38939943899288, 126.7348563976538)
        Wolgot.icon = OverlayImage.fromResource(R.drawable.marker)
        Wolgot.map = naverMap
        Wolgot.setOnClickListener {
            showDetails("월곶 자율방범대  ${Wolgot.position.latitude}, ${Wolgot.position.longitude}")
            true
        }
        //정왕 자율방범대
        val jeong_wang = Marker()
        jeong_wang.position = LatLng(37.34021000000037, 126.73529607903649)
        jeong_wang.icon = OverlayImage.fromResource(R.drawable.marker)
        jeong_wang.map = naverMap
        jeong_wang.setOnClickListener {
            showDetails("정왕 자율방범대  ${jeong_wang.position.latitude}, ${jeong_wang.position.longitude}")
            true
        }
        //시흥경찰서자율방범대대야지대
        val siheung = Marker()
        siheung.position = LatLng(37.444233800000376, 126.77718477903474)
        siheung.icon = OverlayImage.fromResource(R.drawable.marker)
        siheung.map = naverMap
        siheung.setOnClickListener {
            showDetails("시흥 경찰서 자율방범대  ${siheung.position.latitude}, ${siheung.position.longitude}")
            true
        }

        //정왕3동 자율방범대
        val jeong_wang3 = Marker()
        jeong_wang3.position = LatLng(37.34571410000142, 126.71796387903493)
        jeong_wang3.icon = OverlayImage.fromResource(R.drawable.marker)
        jeong_wang3.map = naverMap
        jeong_wang3.setOnClickListener {
            showDetails("정왕3동 자율방범대  ${jeong_wang3.position.latitude}, ${jeong_wang3.position.longitude}")
            true
        }

        // 시흥시 외국인 자율방범대
        val foreigner = Marker()
        foreigner.position = LatLng(37.34757739999961, 126.74654527903799)
        foreigner.icon = OverlayImage.fromResource(R.drawable.marker)
        foreigner.map = naverMap

        foreigner.setOnClickListener {
            showDetails("시흥시 외국인 자율방범대  ${foreigner.position.latitude}, ${foreigner.position.longitude}")
            true
        }

    }

    private fun showDetails(details: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(details)
            .setCancelable(true)
            .setPositiveButton("닫기") { dialog, _ ->
                dialog.dismiss()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("자율방범대 정보")
        alert.show()
    }
    private fun showMarkerDetails(details: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(details)
            .setCancelable(true)
            .setPositiveButton("닫기") { dialog, _ ->
                dialog.dismiss()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("세부 정보")
        alert.show()
    }


    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
        }
    }
}
