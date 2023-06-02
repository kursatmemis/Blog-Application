package com.kursatmemis.blog_application

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kursatmemis.blog_application.models.Tutorial

class GalleryActivity : AppCompatActivity() {
    private lateinit var showSubjectsButton: Button
    private lateinit var subjectExplanationTextView: TextView
    private lateinit var youtubeWebView: WebView
    private val tutorials = mutableMapOf<String, Tutorial>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        showAlertDialog()
        showSubjectsButton = findViewById(R.id.showSubjectsButton)
        subjectExplanationTextView = findViewById(R.id.subjectExplanationTextView)
        createTutorials()
        youtubeWebView = findViewById(R.id.youtubeWebView)
        youtubeWebView.settings.javaScriptEnabled = true
        youtubeWebView.setWebViewClient(WebViewClient())
        showSubjectsButton.setOnClickListener {
            val popupMenu = PopupMenu(this@GalleryActivity, it)
            val menuInflater = getMenuInflater()
            menuInflater.inflate(R.menu.show_subjects_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_what_is_variables -> {
                        youtubeWebView.visibility = View.VISIBLE
                        subjectExplanationTextView.text = getString(R.string.variables_explanation)
                        val videoLink = tutorials.get("what_is_variable")!!.videoLink
                        val html = setHtml(videoLink)
                        youtubeWebView.loadData(html, "text/html", "utf-8")
                        true
                    }
                    R.id.variable_operations -> {
                        youtubeWebView.visibility = View.VISIBLE
                        subjectExplanationTextView.text = getString(R.string.variables_operations_explanation)
                        val videoLink = tutorials.get("variable_operations")!!.videoLink
                        val html = setHtml(videoLink)
                        youtubeWebView.loadData(html, "text/html", "utf-8")
                        true
                    }
                    R.id.action_what_is_if_else -> {
                        youtubeWebView.visibility = View.VISIBLE
                        subjectExplanationTextView.text = getString(R.string.if_else_explanation)
                        val videoLink = tutorials.get("what_is_if_else")!!.videoLink
                        val html = setHtml(videoLink)
                        youtubeWebView.loadData(html, "text/html", "utf-8")
                        true
                    }
                    R.id.action_if_else_example -> {
                        youtubeWebView.visibility = View.VISIBLE
                        subjectExplanationTextView.text = getString(R.string.if_else_explanation)
                        val videoLink = tutorials.get("if_else_example")!!.videoLink
                        val html = setHtml(videoLink)
                        youtubeWebView.loadData(html, "text/html", "utf-8")
                        true
                    }
                    R.id.action_for_while_loop -> {
                        youtubeWebView.visibility = View.VISIBLE
                        subjectExplanationTextView.text = getString(R.string.for_while_loop_explanation)
                        val videoLink = tutorials.get("for_while_loops")!!.videoLink
                        val html = setHtml(videoLink)
                        youtubeWebView.loadData(html, "text/html", "utf-8")
                        true
                    }
                    R.id.action_for_each -> {
                        youtubeWebView.visibility = View.VISIBLE
                        subjectExplanationTextView.text = getString(R.string.for_each_explanation)
                        val videoLink = tutorials.get("for_each")!!.videoLink
                        val html = setHtml(videoLink)
                        youtubeWebView.loadData(html, "text/html", "utf-8")
                        true
                    }
                    R.id.action_do_while -> {
                        youtubeWebView.visibility = View.VISIBLE
                        subjectExplanationTextView.text = getString(R.string.do_while_explanation)
                        val videoLink = tutorials.get("do_while")!!.videoLink
                        val html = setHtml(videoLink)
                        youtubeWebView.loadData(html, "text/html", "utf-8")
                        true
                    }
                    R.id.action_loops_example -> {
                        youtubeWebView.visibility = View.VISIBLE
                        subjectExplanationTextView.text = getString(R.string.for_while_loop_explanation)
                        val videoLink = tutorials.get("example_loops")!!.videoLink
                        val html = setHtml(videoLink)
                        youtubeWebView.loadData(html, "text/html", "utf-8")
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    private fun createTutorials() {
        val tutorialWhatIsVariable = Tutorial(
            getString(R.string.variables_explanation),
            "https://www.youtube.com/embed/zOqP4adLhto"
        )
        val tutorialVariableOperations = Tutorial(
            getString(R.string.variables_operations),
            "https://www.youtube.com/embed/hYkoLDCtJPo" +
                    ""
        )
        val tutorialWhatIsIfElse = Tutorial(
            getString(R.string.if_else_explanation),
            "https://www.youtube.com/embed/DYln46MsVAo"
        )
        val tutorialIfElseExample = Tutorial(
            getString(R.string.if_else_explanation),
            "https://www.youtube.com/embed/xQw2cTP_ccY"
        )
        val tutorialForWhileLoops = Tutorial(
            getString(R.string.for_while_loop_explanation),
            "https://www.youtube.com/embed/ZR0vj8e2UiM"
        )
        val tutorialForEach = Tutorial(
            getString(R.string.for_each_explanation),
            "https://www.youtube.com/embed/R9tPZVhA-7M"
        )
        val tutorialDoWhile = Tutorial(
            getString(R.string.do_while_explanation),
            "https://www.youtube.com/embed/vXbrbrjCbJ4"
        )
        val loopExample = Tutorial(
            getString(R.string.for_while_loop_explanation),
            "https://www.youtube.com/embed/1njKDJ3y2Dk"
        )
        tutorials.put("what_is_variable", tutorialWhatIsVariable)
        tutorials.put("variable_operations", tutorialVariableOperations)
        tutorials.put("what_is_if_else", tutorialWhatIsIfElse)
        tutorials.put("if_else_example", tutorialIfElseExample)
        tutorials.put("for_while_loops", tutorialForWhileLoops)
        tutorials.put("for_each", tutorialForEach)
        tutorials.put("do_while", tutorialDoWhile)
        tutorials.put("example_loops", loopExample)
    }

    private fun setHtml(link: String): String {
        val html =
            "<html><body><iframe width=\"100%\" height=\"100%\" src=\"$link\" frameborder=\"0\" allowfullscreen></iframe></body></html>"
        return html
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this@GalleryActivity)
        builder.setTitle("Bilgilendirme")
        builder.setMessage("Buradaki videolar Metin Buğra Köksal ile ortak hazırladığımız içeriklerden oluşmaktadır.")
        builder.setPositiveButton("Tamamdır!", { dialogInterface, i ->  })
        builder.setCancelable(false)
        builder.show()
    }
}

