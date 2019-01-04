#!/usr/bin/env groovy

def call(String reportFileUrl = null) {
try{
  echo "reportFileUrl ${reportFileUrl}"


  def resultmap = [:]

  //assert result['highs'] == 0

  def xmlfile = new File(reportFileUrl);
  def doc = new XmlSlurper(false, false, true).parse(xmlfile)
  //echo 'Start transforming XML'

  //Get Scan Details (Results, Languages, Queries)
  doc.Query.each { query ->
    //echo "query index: ${query.@QueryPath}"
    query.Result.each { result ->
      //echo "severity index: ${result.@Severity}"
      def severity = "${result.@Severity}"

      switch(severity) {
        case "High":
          echo "High"
          break
        case "Medium":
          echo "Medium"
          break
        case "Low":
          echo "Low"
          break
        case "Information":
          echo "Info"
          break
      }


    }
  }

  //return result
  }catch (Exception ex){
      println "Exception throw"
  }
}
