var FILE_TYPE = {
		"TYPE_EXCEL":{
			suffix:"xls xlsx XLS XLSX",
		    msg:"文件类型不正确！请选择Excel文件。"
		},
		"TYPE_WORD":{
			suffix:"doc docx DOC DOCX",
			msg:"文件类型不正确！请选择Word文件。"
		},
		"TYPE_WORD2007":{
			suffix:"docx DOCX",
		    msg:"文件类型不正确！请选择Word2007文件。"
		}
};

//参数：文件名（包括后缀）、限制的文件类型TYPE_EXCEL|TYPE_WORD|TYPE_WORD2007
function checkType(fileName,fileType) { 
	var fileName = fileName.trim();
	if(fileName && fileName.length > 1 ) {  
	  var ldot = fileName.lastIndexOf(".");
	  var type = fileName.substring(ldot + 1);
	  if(FILE_TYPE[fileType].suffix.indexOf(type) > -1){
		  return true;
	  }
	}
	 alert(FILE_TYPE[fileType].msg);
    return false;
};


