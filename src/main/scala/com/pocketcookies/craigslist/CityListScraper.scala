package com.pocketcookies.craigslist

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import org.jsoup.Jsoup;
import scala.collection.JavaConversions._;

/**
 * @author John Edmonds
 */
object CityListScraper {
  
  def main(args : Array[String]) {
    val in:InputStream = if(args.length < 1 || args(0).equals("-")){
      System.in
	} else {
	  new FileInputStream(new File(args(0)))
    }
    val out:OutputStream = if(args.length < 2 || args(1).equals("-")){
      System.out
    } else {
      new FileOutputStream(new File(args(1)))
    }
    val doc = Jsoup.parse(in, "UTF-8", "")
    val xml = <continents>
    {
      doc.select(".continent_header").map((continent)=>{
        <continent name={continent.text}>
        {
          doc.select(".state_delimiter").map((state)=>{
            <state name={state.text}>
            {
              state.nextElementSibling().select("li").map((city)=>{
                <city name={city.text} value={city.select("a").get(0).attributes().get("href")}/>
              })
            }
            </state>
          })
        }
        </continent>
      })
    }
    </continents>
    out.write(xml.toString().getBytes())
    out.flush()
    out.close()
  }

}
