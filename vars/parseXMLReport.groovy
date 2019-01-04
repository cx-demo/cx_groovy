#!/usr/bin/env groovy

def call(String reportFileUrl = null) {
try{
  echo "reportFileUrl ${reportFileUrl}"


  def resultMap = [highs:0, mediums:0, lows:0, infos:0]

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
          resultMap['highs'] += 1
          break
        case "Medium":
          echo "Medium"
          resultMap['mediums'] += 1
          break
        case "Low":
          echo "Low"
          resultMap['lows'] += 1
          break
        case "Information":
          echo "Info"
          resultMap['infos'] += 1
          break
      }


    }
  }
  return resultMap

  }catch (Exception ex){
      println "Exception throw"
  }
}
