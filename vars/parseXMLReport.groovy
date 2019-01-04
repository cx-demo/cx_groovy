#!/usr/bin/env groovy

def call(String reportFileUrl = null) {
  //echo "Hello, ${name}."

  def result = [highs: 0, mediums: 0, lows: 0, infos: 0]

  def xmlfile = new File(reportFileUrl);
  def doc = new XmlSlurper(false, false, true).parse(xmlfile)
  //echo 'Start transforming XML'

  //Get Scan Details (Results, Languages, Queries)
  doc.Query.each { query ->
    //println "query index: ${query.@QueryPath}"
    query.Result.each { result ->
      println "severity index: ${result.@Severity}"
      def severity = "${result.@Severity}"

/*
try{
      switch(severity) {
        case "High":
          result[highs]++
          break
        case "Medium":
          result[mediums]++
          break
        case "Low":
          result[lows]++
          break
        case "Information":
          result[infos]++
          break
      }

}catch (Exception e){
    println e.toString();
}
*/
    }
  }

  return result

}
