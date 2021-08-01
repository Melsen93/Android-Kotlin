package com.example.androidonkotlin.viewdetails

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidonkotlin.R
import com.example.androidonkotlin.databinding.FragmentDetailsBinding
import com.example.androidonkotlin.model.Weather
import com.example.androidonkotlin.utils.showSnackBar
import com.example.androidonkotlin.viewmodel.AppState
import com.example.androidonkotlin.viewmodel.DetailsViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }
    private lateinit var chosenHeaderPicture: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getWeatherFromRemoteSource(
                            weatherBundle.city.lat,
                            weatherBundle.city.lon
                        )
                    })
            }
        }
    }

    private fun setWeather(weather: Weather) {
        with(binding) {
            val city = weatherBundle.city
            cityName.text = city.cityName
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            weatherCondition.text = weather.condition

            weather.icon?.let {
                GlideToVectorYou.justLoadImage(
                    activity,
                    Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${it}.svg"),
                    weatherIcon
                )
            }

            getHeaderPicture(city.cityName)
            Picasso
                .get()
                .load(chosenHeaderPicture)
                .into(headerIcon)
        }
    }

    private fun getHeaderPicture(cityName: String) {
        when (cityName) {
            "Москва" -> chosenHeaderPicture = "https://freepngimg.com/download/temple/" +
                    "93582-building-basils-moscow-petersburg-saint-landmark-cathedral.png"
            "Санкт-Петербург" -> chosenHeaderPicture =
                "https://e7.pngegg.com/pngimages/170/463/png-" +
                        "clipart-saint-petersburg-skyline-silhouette-animals-building-thumbnail.png"
            "Новосибирск" -> chosenHeaderPicture = "https://sun9-28.userapi.com/" +
                    "4hHJFGDn8MIZhkqZZgxc7RR0Z7l1MYuif9djrQ/UHnmoOXypso.jpg"
            "Екатеринбург" -> chosenHeaderPicture = "https://unipo.ru/assets/cache/images/" +
                    "ekb1-540x250-1c0.png"
            "Нижний Новгород" -> chosenHeaderPicture = "https://russouvenirs.ru/files/tovar/" +
                    "3000_3100/3062/025-10f-76k21_anons.jpg"
            "Казань" -> chosenHeaderPicture = "https://skazka-deti.ru/upload/iblock/6c0/" +
                    "6c02cb5c2904889f17b2667261953223.jpg"
            "Челябинск" -> chosenHeaderPicture = "https://bezopasnost112.ru/wp-content/uploads/" +
                    "2020/07/12bb932701244df7827bb8c6fbfeeef0-330x206.jpg"
            "Омск" -> chosenHeaderPicture = "https://argonek55.ru/wp-content/uploads/2019/01/" +
                    "cropped-1233-768x204.png"
            "Ростов-на-Дону" -> chosenHeaderPicture =
                "https://zalog124.ru/wp-content/uploads/2018/" +
                        "06/zaimpodnedvij-768x203.jpg"
            "Хабаровск" -> chosenHeaderPicture = "https://www.todaykhv.ru/upload/resized/6b8/" +
                    "6b8e94d8f21cf0169af1ab0e56a88c86.jpg"
            "Лондон" -> chosenHeaderPicture =
                "https://1.bp.blogspot.com/-zI_fpKSz44s/XnIXHJH3fWI/" +
                        "AAAAAAAAAPQ/cSuEIBVjAzoP7uwizmonBxwbD7NT6MDDwCLcBGAsYHQ/s320/big-ben-clipart-14.png"
            "Токио" -> chosenHeaderPicture = "https://png.pngitem.com/pimgs/s/" +
                    "139-1399802_tokyo-japan-aljanh-tokyo-skyline-with-grey-buildings.png"
            "Париж" -> chosenHeaderPicture = "https://c7.hotpng.com/preview/170/693/168/eiffel-" +
                    "tower-arc-de-triomphe-belxe9m-tower-wallpaper-eiffel-tower-in-paris-three-" +
                    "thumbnail.jpg"
            "Берлин" -> chosenHeaderPicture =
                "https://secure.meetupstatic.com/photos/event/8/d/f/5/" +
                        "600_475296341.jpeg"
            "Рим" -> chosenHeaderPicture = "https://w7.pngwing.com/pngs/363/138/png-transparent-" +
                    "colosseum-roman-forum-pantheon-amphitheatrum-castrense-amphitheater-scenic-" +
                    "colosseum-building-medieval-architecture-rome-thumbnail.png"
            "Минск" -> chosenHeaderPicture = "https://prometr.by/upload/medialibrary/3a2/" +
                    "3a2f4e848c7bdfe2998ef2f87f6658e6.jpg"
            "Стамбул" -> chosenHeaderPicture = "https://www.pngkey.com/png/detail/" +
                    "160-1607821_open-istanbul-vector-png.png"
            "Вашингтон" -> chosenHeaderPicture =
                "https://w7.pngwing.com/pngs/92/441/png-transparent" +
                        "-united-states-capitol-dome-capitol-records-building-texas-state-capitol-united" +
                        "-states-congress-hill-building-united-states-landmark-thumbnail.png"
            "Киев" -> chosenHeaderPicture = "https://www.touropia.com/gfx/b/2019/10/" +
                    "ukraine_places-350x200.jpg"
            "Пекин" -> chosenHeaderPicture =
                "https://img.lovepik.com/element/40038/9227.png_300.png"
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
