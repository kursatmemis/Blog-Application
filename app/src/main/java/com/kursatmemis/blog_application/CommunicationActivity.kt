package com.kursatmemis.blog_application

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kursatmemis.blog_application.adapters.CommunicationListViewItemAdapter
import com.kursatmemis.blog_application.models.CommunicationListViewItem
import com.shashank.sony.fancytoastlib.FancyToast

class CommunicationActivity : AppCompatActivity() {
    private var communicationItems = mutableListOf<CommunicationListViewItem>()
    private lateinit var listView: ListView
    private lateinit var getAnswerEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)
        createDataSource()
        listView = findViewById(R.id.communicationActivityListView)

        val adapter = CommunicationListViewItemAdapter(this, communicationItems)
        listView.adapter = adapter
    }

    private fun createDataSource() {
        val instagram =
            CommunicationListViewItem(
                "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw8QDxAOEBAPDw8QEBAQDw8PEA8PDw8PFRIWFhUVFhUYHSggGBolGxYVITEhJikrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGi0dHyUtLS0tLS0tLS0vLSsvLS0tLSstLS0tLS0tLS0tLS0tKystLS0rLS0tLS0tLSstKy0tLf/AABEIAOEA4QMBIgACEQEDEQH/xAAcAAABBAMBAAAAAAAAAAAAAAAAAQMGBwIEBQj/xABREAABAwIBBwUHDQ4GAwAAAAABAAIDBBESBQYhMUFRYQcTcYGRIjJSkqGzwSMkQlNicnSCoqOxwtIUFRYXJTRUg4Syw9HT4jNDRGNk8HOT4f/EABoBAAEFAQAAAAAAAAAAAAAAAAMBAgQFBgD/xAA4EQABAwEEBggFBAIDAAAAAAABAAIDEQQSITEFE0FRcbEiUmGBkaHR8BQyNMHhFSNyskKCJDNi/9oADAMBAAIRAxEAPwC8UIWtXVccMbpZHBjGC7nHUB6ehdngFy2VFMtZ9UlPdjL1Eg0WjIEYO4yauy6hOdWeM1WXRR4oqbSMA0PlG+QjZ7nVvuoxdXNn0YPmm8PX0HigPkOTVLq/P6ukvgMcDdnNsDnW4uffyALjTZerHm7qqc8Odka3sBsuZdF1ZMgjZ8rQO5BIccyth9XIdckh6XuPpTZed57SmcSLomAyXatO4kmJN3RdJVOEScxIxLC6Lpt5EbCs8SMSwui6SqM2FZ4kYlhdLdJeRWwJcSMSwui6SqKIVliSYkl0l11UVsKzxHeVk2pkGqR46HuHpTV0LqoogW9DlurZpbVVLbbBNLbsvZdehz8yhEe6kZO3wZmN1e+ZY9t1GUJjo43YOaD3J3w7dytXIvKFSykMnaaZ59kTjhJ99rb1iw3qYxyBwDmkOaQCHAggg6iDtXnldzNrOeoonAA85AT3cLj3PEsPsHeQ7RugT6OaRWLA7jl74oElk2sV3IXPyRlSGqhbPC7Ew6CDocx21rhsI/7oXQVSQQaFQiKYFCEISJEKm8+s5jVzGKN3rWJxDLHRK8aDIeGsDhp2qa8pWWTTUfNsNpakmMW1iMD1Rw6iG/HVPXVxoyzimtd3ev2C4tJTuJJdYYkYlbVXapOXS3TN1liSEpwhWeJLdNXRdNqiCBO3S3WVLSSy/wCFFLJ/4o3yfugrpRZsZQdpFJP8ZjmfvWQ3SAZkDvCIIgM1y7pLrttzNykf9K/rfEPpcnBmRlT9FP8A7qf7aFr4+sPEIrWM3jxXBui67/4EZT/Rfnab7aPwIyn+jfPU320mvj6w8R6oobH1h4j1Ufuluu/+BGU/0b56m+2j8B8p/o3ztN9tJr4+sPEeqINV1h4j1Ufuluu/+BGU/wBG+epvtrF2ZmUh/pXdUkJ+hyTXx9YeITwYusPEeq4KF2JM1soN10k3xWY/3SVoVOT6iIXlhljG+WKSMfKAThIDkR4hFaGnIgrWSWWQSp95PuLFCySLryS6iySyVKnXl11dnNXLz6KcPF3RPs2aMeyZ4QHhDWOsbVdNPO2RjZGODmPaHMcNIc0i4I6l58Vl8l+Vy+N9G83MXqkV9fNOPdDqcR443Kvt8IcNYMxnw/HLgoFtg6N8bM+CnyEIVSqxU1yoVxlygYr9zTxsjA2Y3DG49jmj4qiK6ecs5krat503qZgPeh5a3yALm2WohFyNrRuUtrcEJEtktk4uRA1YrJFlZOYeZYsysq2XJs6GBw0NGx7xtO5uzWdOoE1obE285K6jBUrgZt5jVNUGyyet4TpDntPOSD3LN3E8CAVYmSszKCnAIhEzxb1SotK6+8A9y09ACkaFSy2uSTbQbgoj5XO7AsWtAFgAANQGgBZLl5Qy9RwEtlqImOGtmIOkHxBc+RcmXP8AycNUj3+9hkH7wCCI3kYA+CRsL3CrWk9ylSFDX8otCNTal3RGwfS8LA8pVF7VV+JD/UTtTJuT/hZuqVNUKFfjLovaavxIP6iPxl0XtVX4kH9RdqZNyX4SbqlTVChf4yqL2qr8SD+ok/GVRe1VfiQf1F2pk6pS/Bz9UqaoUK/GVRe1VfiQ/wBRZM5RqI621LelkfoeV2pk3LvhJ+oVM0KKR5/5POt8jffRuP7t106LOShmsI6mIuOprnc289DX2KaY3jEgobrPK0VcwjuKbynmrRVFzJAxrz/mRARSX3kt19d1Bsv5gTwgyUzjUMGkssBMB0DQ/qseBVqoT47RIzI4bk+G1SRZGo3FedyhWpnpmg2oDqiBobUAXewWDagDf7vcduo7CKtc0jQQQRoIIIIO0EbFaRTCQVCvLPM2Zt5veNyxRZKhGvI11Iu3mbW8xXU7r2D5BE7i1/caeAJaepcVKyQtc1ze+aQ5vSNISnpAt34Jjo7wLd+C9DIWj99Yt5Qs9isxQqgqx2KWR3hSPPa4lNJXnSekpFpKqyDcEJUBLZNLkZrFKuT7IAqqjnJBeCDC9wOqSQ94ziNBJ6ANquRR3MTJop6GEWs+UCeTRY4ngEA8Q3COpSJUlpl1jzuGSrZ33nncFpZVylFTROnmdhY3rLjsa0bSdyqbOLPOpqyWsc6CHZHG4hzh7t40noGjp1oz8y6aupcxp9Qhc6OMDU5w0PfxudA4Abyo4pMEIaKnPkrKy2QNAc4Y8kmpCySKQXKxDUISoTbyeGoRZKhNvJ91JZFlkhLeS3VihZWSWS3k66kQSuxm3kCStlLGkMYwAySEXDAdQA2uNjYcCp8zk8oQzCXTl3h44wb8Bht5Ex07WGhUWe1wwOuuOPZs97s1BchZ0VVIQGvMkQ1wyEujtuadberRvBVr5By1FWQiWM2I0SRutjjduPoO1VfnZmtJROa4O5yBxsx9rOa7XhcN9r2I12OpaWbOWH0dSyYXLO9lYPZxk6dG0jWOI4lDkjbK283Pnx7UC0WWO1R6yLPZTb2Ht8/FXkqx5SshCN7ayMWbIcMoGoSWuH/GAN+IG1ysqOQOAcCCCAQRqIOorSy3k8VNNNAbeqMIaT7F40sd1OAPUosMlxwKprLPqZQ7ZkeHvEdqolCyIN7EWI0EHWDtCRW9VqC1CEITgUlFLvvsd6FGecO9CBqQoPwjdy551pLLI60AKQXIDWpAtiip+dljh9skjj8dwb6UyF2M0YsWUKQH2+N3inF6EF76AlGpdaTu9FeTGgAAaABYDcFzs46wwUdRMDZzIn4DukIsz5RC6aivKRJbJ0g8N8Tex4d9VVDBVwCo4WB0jW7yB5qn0qEKyLlqQ1CVCVMvIgakSp6kppJXtiiY573GzWNFyf5DidAViZA5P42ASVZ5x+vmmEtjb75w0uPYOlMdIG5oU9oigFXnu2++NFXVPTSSOwxske7wWNc53Y3SutDmllF4u2lkHvy2M9jnBXJSUscTQyKNkbRqaxoY3sC2EAznYFVv0u6vQYO+p5UVKyZoZRaLmmf8VzHnsaSuXV0U0RtLHJETqEjHsJ6MQ0q/k1PE17Sx7WvadBa4BzSOIK7XncuZph9emwHhhzqqAQrSy9mHTzAvp/W8uvD/AJDjxb7D4ujgVW+UsnTU0hhmYWOGkX0hzfCadRH/AHWjNkDslbWa1RWgdA47jn74KyOTFrfuJxHfGofi33DGWHZY9amSpvNHOR1E9wcC6GS2NgtiaRqe2+i+y23RuVhszzycW4vuho3tLJA/xbXPUo8jDeqqS32ObXOc1pcDuBPcfeSM+WtOTqnHawa0t98JG4fLYdappS3PLOv7rAghDmwNdiJdodI4Xto2NGu3RqsolZSYAWtxVro6zvhho/Ak1puyVx5hVploIbm7osUJ4YT3A8QtUjUI5KpPW07N0+Lxo2j6qm6iSCjyqC2sDbQ8DfzVH52U3NV9SzZzrpBwEln+lclSjlHjtlB58OOJx6cOH6qi6sonVYOC01nN+JjuwckIWSESqIQlQhCWqZdWqVkkOtKkc5QWtQu9mI2+UqUe7eeyNx9C4SkPJ+PypTdM3mJEGR3RPBPlb+0/+J5FXSodyoutQtHhVEY+RIfQpioXyq/mUXwpvmpVXM+YKjsYraGcVVSVCVTLy1gahPUlK+WRkUbS573BrGjaT9A1knYAUyrL5NMhhkZrHju5Lsiv7GMEYndLiLdDRvQ3PoEK1TCzxF57hvPvE8F3c183oqGKws+Z4HOy20k+C3c0butd9CjWducrKJga0B9Q8XjYdTRe2N9tm4ayRwJEbElZYCW0y0HScffcB4ALt1tZFEzHLIyNmrFI4NF92nWeCj1Vn9k9hs10stva47D5eFVflHKM1RIZZpHPcdWLU0bgNQHALURAwUxV5DoWMD9xxJ7MByqeOCtin5QaB3fc9FxfGCPkFxUhyflOCduKCWOUDXgcCW8HDW09Koayepql8TxJG9zHt71zCWuHC+7hqK7VhOl0LER+2S09uI5VXoBcrLmRoauIxSDTpLHgd3G7eD9I2ri5m52tqwIJiG1DRdpHctnAGkgbHDaOsbQJeh4gqhkjls0lHYOHuoVD5XybJSzPglFnA6CO9ew96W8D6CNi01bOf2RRUUzpWD1aAF4trfHbu28dGkcRbaVUykMfULU2G0/ExB20YHj+c0qEiVPBUuisTknd3FU3c6E9oePQp+q85JtdZ0wfxVYajSfMVk9KClqf3f1CqnlQFq5nGnjPy5B6FD1MuVP8+j+DR+dlUPUyE9ALQWL6dnBCRCVHBUhKhCE5DotcpQlwpwNQXPCjNam7KQ8n7fynTfrvMSLhhikWYTfylTfrvMPUZ8tQQnTD9l/8XciriUM5U/zKL4U3zMqmah/KcPWcXwlvmpVGrTFZ6wfUs4/ZVXhS4U9hRhXa5a4LGmpnSSMjb3z5GNb74kNHlKvekp2xRsiYLMjY2No3NaLD6FUeZ9OHZQpgfDL+tjXOHlarkXB95UOmn9JjOyvjh9lrVtU2KOSZ3exsc92+zRfQqNypWyVE0k8pu57i617ho2AcLWA6FafKJOW0Dmj/ADJGM6hd/wBRVOWrg8AqToSECJ0hzJp3Cn3z4JtCcLVgWp4kV5RIhFkJwclAWcEzmOa9jiHMIcxw1tcDcEK7c3spCqpopwAC9tngamyNNnjouDbhZUerJ5Kqi8FRF4ErXj4zbfw0j1UaZgDoNZtafImnMhTtUXnDQ/c9VPCNDWPdgG5h0tHiuar0VT8pkIbXYvbII3HpxOb9DAkYcVXaEkImczYR5j8VUUSJUIwWmorA5JtdZ+z/AMVWGq75JddZ+z/xVYiC/wCZZHSv1b/9f6hVZypD17H8Fj85MocpjypfnsfwaPzsqh6kQnohaCw/TM4JEIQpAKkpUIQnJlFlgShqcwrMNVU+ZAaE2GqQZit/KNP+t8w9cUNXfzJb+UIP1vmZFH1lSkn/AOl/8XcirYUR5Sh60i+Et81IpconyjD1pH8Ib5qRElNGErOaP+pZx+xVaYUYU9hRhUHWrXrqZnkNr6c+6cPGYWj6VbqpWilMUsco0mN7Hgby1wNvIrlilD2te03a4BzTvBFwVJs771Qs/ptnTY/eKeGP3Ub5RYMVDfZHKx56Ddn0vCq4tV3ZTpGzwyQu0CRhbfwTsPUbHqVOVVM6N7o3jC5ri1w3EehJObprvUvQswdE6PaDXuP5WmWpC1PlqxLUNsiukwWpstWyWpstRmyJwTBCsfkpprQ1Eux8kTB0saT9cKvhGT3IBJOgAC5J3Ab1cmauTPuWlihPf2xSav8AEdrF9ttDepHa6qqtNTBlmubXEeWJ+3iu0qo5TpAa5o8GCNp6cT3fWCtdUhnTWietqJQbtc8sadYLGANaRwIAPWnNzVZoOMunc7c3mQuUlSIRQVqKKwOSXXWfs/8AFViKu+SXXWfs/wDFViIb81j9L/WP/wBf6hVZypfnsfwVnnJVD1MeVH89j+Cx+dlUNRovlHvatDYR/wAaPggoSoKkNKkoQhCemLdwrMNTmFKGrNPkQQsQ1dzMsflCD9b5l65IauxmloroDxf5Y3BDbJ028RzTJ/8Apf8AxdyKtJRblDHrWP4Q3zcilKjOfjb0rOE7T8h49KsLQaROPYs1YPqWcVXWFGFO4UYVSaxa5NYVYWY+VOcg5hx9Uh1b3RX0Hq1eLvUCsn8n1b4JWzRmxaepw2tPAosNo1bwdm1R7ZZhaIizbmOPvBW+otnbm4Kkc9EAJ2ixGgCZo1C+xw2HqOy3ZyTlKOojEjDwcw98x24/z2roK5IbI3eCsrHJLZpajBw90KpKaFzHFjmlpabOa4EOB4gpstVwZRyRT1AtLGHEaA/vXjocNNuGpR+ozChP+HNIzg5rX/RhUF1mkaejitFBpiB4/cq095HdSp8lXZakwXIA0kkAAaSSdQCsKHMGMH1Sd7huYxrD2kuXeyXkCmptMUYxe2P7uTqOzqsnshk24J0umbOwdCrj2YeZ9FHczc0zEW1VQ3u9cURGlh8N/utw2a9eqcIWplCujp43TSuwsb2k7ABtJ3KY1oaFnbRaJbVLediTgAPIAe65nFcnPPLApaVxBtLKDHDbWCRpf8Uaemw2qnNWhdrOLK8lZO6V2hnexsvcMZsHSdZPosuQ5qa2SpWs0bZPhobp+Y4n07uddiwQghCOCp6sDklH55+z/wAVWIq+5Jx3NW7eYR2B59KsFNdmsbpb6x/+v9Qqs5Uvz2P4LH52VQ5TDlRd69Zwp4x8uQ+lRBFj+VaOwj/jR8EIQhGBUgpUIQiVTaLrhqyDVkGrMNWQdIgBIGrpZudzWU592B26PStENT9DJgljk8CRjvFcD6EISXXAneke280t3gjyVtLgZ6NvRuPgvYfLb0rvrnZcpucppmDSSwkDe5vdDygK/tDSY3AbjyWSszwyZjjsI5qr8KMKdwpMKy99bJYYVjhTuFGFdfS1WeT62WneJInYTqI9i4bnDaFOckZzwTANkIhl3OIDHH3LvQfKoFZYFqkwWt8WWI3KLabHFaPmz3jNXChVTR5TqIdEUr2gexvdnim48i6cWeFW0aeafxcw3+SQrNmkoyOkCPP34KnfoaUHouBHh681YaFXkmedWdQhbxax1/K4rk12W6qYWfM+x1taQxpHENsD1p5t8ewErmaGmJ6TgPE/b7qeZYzmp6YEF3OSj/KjIJB098dTfp4KucuZYmq3YpD3Avgjboa0cBtPE/8AxaxamnNQHWlz88ld2SwRWbFuLt5+27n20Wu5qwcFsOCacEVj1YLXc1Yp5wTTgpjHpysnkri9bzu3zNb4sYP1lOVGeT2k5vJ8ZIsZXOlI6e5B6w0HrUmRFiNIvDrVIRvp4YKpOUiS+UHDwYom+Qu+sosuznlUc5lCpdrAkwdcYbGfK1cdGbkFrbKy7Axv/kcgkSoQiBESoSoT6pq7+FZBqelZZ7huc4eVAasS92Kig4LANWWFOBqyDUFzktVYWQarnaeJ2shoY7fiboPba/WumoXmlX828wuNmSEYdwk1DtGjqCmi0tin1sQdtGB4/nPvWWtkOqlI2HEd/pkq6y/k0wTuAHcOJfGdlidXVq7N65uFWVlGgZUR4H9LXDW128KFV+SpYDZze52PGljuvYeBVJbrI6Fxe0dHl2HcNxyorqxW4StDXHpc+0LlYEYVs82jm1Aqp19apYsSxbRjSGNLVOvrTLViWraMawdGngp4etUhNkLacxNOaitciA1WuQm3BPkLBwR2uTwUw4JpwWw4JpwUpjk5a7gtrI+TH1U7IGX7p3du8Fo1u6h5bDatjJuSZ6l+CJhd4T9TG8XHUPp3XVmZt5Ajo4yB3crrc5JbX7lu5oU+GruChW7SDbK2gxfsG7tO775CoK61PC2NjI2DC1jWsa3YGtFgOwJnKdY2CCSd2qNjn23kDQOkmw61uKveUzLQs2hYbklr57bBrYw9fddTd6lrLWOzm0TBneeGZVfyPc4uc43LiXOO9xNye1IkQjBbgpUiEJ4TE9zaFIPvWdyVJfUD4ti6GVIsNRM3dLJ2YzZMNauznRTYKlx2SBrx2WPlae1cxrFjLQC2Rw7ShRSX42u3gLAMTgYnWsTjWKOU4uTLWKX5DyrzgEch9UGgE+zH81GgxZtYjWa0OgfebjvG9RbRE2Zt13cdynqwc0EWIBB1g6QVH6DLbm2bKC4eGNY6Rt/7rXbp6pkneODuG0dI1rSQWuKb5DjuOfvtGCopYHxnpDDfs98Vp1GRKd+nBhO9hLfJq8i1XZsxbHyDpwn0Bd5C59jgcalgrwpyTm2qZuTj74qOHNYbJj1sB9KwOaf+/wDNf3KTIQzo6zdXzd6p4t9oH+XkPRRg5pf7/wA1/csTmh/v/Nf3KUoSfp1m6vm71Tv1G09byHoomczf+R81/esDmV/yPmT9tS9Cd+n2fq+bvVO/U7T1vIeihxzG/wCT8z/esfwEbtqXdUYH1lM0JRYYB/j5n1S/qlq6/k30URZmLB7KaU+9DG/SCtymzQoo7ExulI2yOuOtrbA9ikSEVtnibk1DdpC0uFC892HKiZhhYxoYxrWNGprAGtHQAnlqVldDCLyyMjGzE4AnoGs9Sh+XM+bAspWknVz0jbAcWtP0u7CnukazAplnsk1oPQFe05eK6+dWcbKOMtbZ1Q4eps1hvu3jdw29pFSVMjnuc97iXuJc5x1ucdJJWxUyOe5z3uLnk3c5xJc47yVquCRslStbYbEyysoMSczv7BuA88z2YISoUkKYUiA0kgAXJIAG8nUhdbNSk56up2bBI17vex92b9IFutPrTFDe+40vOwE+GKtr7yRJF1UKMsHrH71ws6KLHEJQO6iOn3h19hse1RZjFYbmgggi4Ogg6iFEco5PMUlvYHSw8N3SFS6Ts5B1oy28dh+3grSwWjo6o7MuC0GsTjWLNrE41iqKKcXJsMTgYnAxZBi66hlyaDEoYng1LhS0TbyyZWTDVI7rOL6U8MqzD2QPS0eha+FGFHbPM3J5HefVDLGOzaPALb+/M3uOw/zSffuXdH2O+0tQtSFqJ8XaOuUmpi6oW2cuS+DH2O+0sDl6bwYux/2lpliwcxd8ZaOuU8QxdULdOcM3gxdj/tLA5yT+DD4r/tLQcxNPYu+Mn65RGwQ9ULfOdNR4EPiv+0mnZ11Pgwj4r/tLnvYmHsTvi5usUZtng6g8FuTZ1Vewsb0Rj03XMq8vVrxY1EjfeYY/3AFjIxa0jE8TyOzcfFS44IW4hg8AtGVxJLnEuJ1ucSSeklMOC2ZGpl4R4ypwK13BMuCfcE24KfGU4LWchZOWKnsSlKrA5L8lG0lY4aCDFFxFwXu7Q0dTlDsg5IfVzthZoB0vdsYy/dE+gbSQrqoKRkETIYxZkbQ1o1mw2k7TtJSuOFFSaYtQZHqRm7PsH55LaQhCEswhM1VO2RpY4XB7Qd44p5CQgEUKUEg1Ci1XQOiNjpbscNR/kU0GqVuaCLEAg6wdS58+SgdLDh4HT5VSz6NcDWLEbtv558VPjtgIo9ccNWYatp1HI32J6dnkTeFV7oyz5hTjgj3wcsU0GpcKdDVlhXXUhcmcKTCn8KMK66uvJgtWBatgtWJauolvLXLVg5q2S1Nuakonhy1XNTbmrac1NuauRWuWi9qYexbz2ph7VyOxy0JGLVkYuhI1asjURqktcudK1akjV0pWrTlClMKlMK0nBMvWy5pJsBcnUBpJW7SZuVkx7iCQDwnt5tva+wPUrCJEc9rBVxA4mnNcN63cjZFnq5ebibceyecQYwbyfRrKmmSOT0XDqqQO/wBuImx6XuF+wDpU2oaOKFgiiY2NjdTWiw4nieKntNAqq1aYjYKQ9I79g9eXHJaOb2Q4qOLm2d042MkhFnSO9AGwbOm5PYQhIsy97nuLnGpKEIQuTUIQhcuQhCFy5Cwl1IQnH5SuGa5sy1yhCz0/zKwZkhCEKOnIWCVCROCbKxchC5PCacmnpUJERqZeteRCFyOFrSrWkSoT2qQxazlt0WsIQptnzSz/AClTnIvedS6SEK4bkstJ85QhCEqahCELlyEIQuXL/9k=",
                "Instagram",
                "goToInstagram",
                "Profile Git"
            )
        val twitter = CommunicationListViewItem(
            "data:image/jpeg;base64,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",
            "Twitter",
            "goToTwitter",
            "Profile Git"
        )

        val phoneCall = CommunicationListViewItem(
            "data:image/png;base64,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",
            "Ara",
            "callPhone",
            "Ara"
        )

        communicationItems.add(instagram)
        communicationItems.add(twitter)
        communicationItems.add(phoneCall)

    }

    fun onClickCommunicationButton(view: View?) {
        val intent: Intent?
        when (view?.tag) {
            "goToInstagram" -> {
                val profileUrl = "https://www.instagram.com/_kursat_memiss/"
                intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(profileUrl))
            }
            "goToTwitter" -> {
                val profileUrl = "https://twitter.com/kursat_memiss"
                intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(profileUrl))
            }
            "callPhone" -> {
                val number = Uri.parse("tel:+905528150269")
                intent = Intent(Intent.ACTION_DIAL, number)
            }
            else -> {
                intent = null
                FancyToast.makeText(
                    this@CommunicationActivity,
                    "Hedef sayfa bulunamadı!",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    true
                ).show()
            }
        }
        if (intent != null) {
            showAlertDialog(intent)
        }
    }

    fun showAlertDialog(intent: Intent) {
        val customAlertDialogTitle =
            layoutInflater.inflate(R.layout.custom_alert_dialog_title, null)
        val customAlertDialogView = layoutInflater.inflate(R.layout.custom_alert_dialog_view, null)
        getAnswerEditText = customAlertDialogView.findViewById(R.id.getAnswerEditText)

        val builder = AlertDialog.Builder(this, R.style.custom_alert_dialog_theme)
        builder.setCustomTitle(customAlertDialogTitle)
        builder.setMessage("Başka bir uygulamaya yönlendiriliyorsunuz.\nDevam etmek istediğinizden emin misiniz?")
        builder.setView(customAlertDialogView)
        builder.setPositiveButton(R.string.positiveButtonText, { dialogInterface, i ->
            if (getAnswerEditText.text.toString().equals("evet")) {
                FancyToast.makeText(
                    this,
                    "Onayınız Alındı!",
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    true
                ).show()
                startActivity(intent)
            } else {
                FancyToast.makeText(
                    this,
                    "Hatalı bir giriş yaptınız.\nLütfen 'evet' yazınız.!",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            }
        })
        builder.setNegativeButton(R.string.negativeButtonText, { dialogInterface, i ->
            FancyToast.makeText(
                this,
                "İşlemden Vazgeçildi!",
                FancyToast.LENGTH_LONG,
                FancyToast.DEFAULT,
                true
            ).show()
        })
        builder.setCancelable(false)
        builder.show()
    }
}