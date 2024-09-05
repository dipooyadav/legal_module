// for Single Control  
function getsupMulti(evt,venType,chkId){
	debugger;
   var id = evt.id;
   var namesup = evt.name;
  if(typeof venType == 'undefined' || venType == null){
	  venType="";
  }
  if(typeof chkId == 'undefined' || chkId == null){
	  chkId="supgst_kid";
  }
  var columns = [{name: 'Supplier Code/Name', minWidth: '350px'},{name: 'Sales Off Code', minWidth: '190px'}, {name: 'GST Number ', minWidth:'100px'},{name: 'PAN Number', minWidth: '100px'},{name: 'Address', minWidth: '200px'},{name: 'City', minWidth: '100px'},{name: 'State', minWidth: '120px'}];
  $('#'+id ).mcautocomplete({
  
  //$("#submitby").mcautocomplete({
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',
			url: appcontext+"/newOrderForm.do?action=searchSupplierMultiCol&accHead="+request.term+"&venType="+venType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 /* select: function (event, ui) {
		  event.preventDefault();
		  var url = ui.item.value;
		  if (url != '#') {
			  location.href = url;
		  }
	  }*/
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  if(namesup=='supName'){
				  setDataOnClickForSuppl(ui.item[10],ui.item[9])
			  }else if(namesup=='Suppliername'){
				  if(chkId=="assupmst_kid"){
					  setSupplierDetail(ui.item[9])
				  }else{

					  setSupplierDetail(ui.item[10])
				  }
			  }else if(namesup=='Agentname'){
				  setAgentDetails(ui.item[10])
			  }else if(namesup=='vendorClientName'  || namesup=='advPartyName' || namesup=='supplierName'){
				  if(chkId=="assupmst_kid"){
					  setDataOnClickForSupplier(ui.item[9],ui.item[10])
				  }else{
				  //if(venType=ddd='S')
				  if (!ui.item) {
					  // Handle the "Create GL" link
					  var createGLItem = {
						  value: "/CobaSys/SupplierMaster.do?action=getSupplier&param=21513", // Set the value to your target URL
						  label: "Create GL", // Set the label for display
						  // Add any other properties that match your data structure
						  // For example, you might need to set an 'id' or 'class' property
					  };
			  
					  // Use the manually created item to perform actions
					  var selectedItem = createGLItem;
			  
					  // Access the properties of the selected item
					  var itemValue = selectedItem.value; // The item's value
					  var itemLabel = selectedItem.label; // The item's label
					  // Add any other properties as needed
			  
					  // Use the properties to perform actions, such as redirection
					  window.location.href = itemValue; // Redirect to the specified URL
				  }
					  setDataOnClickForSupplier(ui.item[10])
				  }
			  }else if(namesup=='vendorClientNameSup'){
				  setDataOnClickForSupplier(ui.item[9])
				  $('#vendorClientNameId').val(ui.item[9])
			  }else if(namesup=='vendorClientNamePayRec'){
					  setDataOnClickForSupplier(ui.item[9],ui.item[10])
			  }else if(namesup=='shipSupplier'){
				  setShipAddressForSupplier(ui.item[10])
				  
			  }
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;
   
	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}


//// for Tables in multiple line 
function getsupMultiForMultipleLines(evt,venType,chkId){
   var id = evt.id;
   var tableid = $(evt).closest('table').attr('id')
   var rowIndex = evt.parentNode.parentNode.rowIndex;
   var trcnt = document.getElementById(tableid).rows[rowIndex];
   var namesup = evt.name;
  if(typeof venType == 'undefined' || venType == null){
	  venType="";
  }
  if(typeof chkId == 'undefined' || chkId == null){
	  chkId="supgst_kid";
  }
  var columns = [{name: 'Supplier Code/Name', minWidth: '350px'},{name: 'Sales Off Code', minWidth: '190px'}, {name: 'GST Number ', minWidth:'100px'},{name: 'PAN Number', minWidth: '100px'},{name: 'Address', minWidth: '200px'},{name: 'City', minWidth: '100px'},{name: 'State', minWidth: '120px'}];
  $(trcnt).find('#'+id ).mcautocomplete({
  
  //$("#submitby").mcautocomplete({
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',
			url: appcontext+"/newOrderForm.do?action=searchSupplierMultiCol&accHead="+request.term+"&venType="+venType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 /* select: function (event, ui) {
		  event.preventDefault();
		  var url = ui.item.value;
		  if (url != '#') {
			  location.href = url;
		  }
	  }*/
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  if(namesup=='supName'){
				  setDataOnClickForSuppl(ui.item[10])
			  }else if(namesup=='Suppliername'){
				  setSupplierDetail(ui.item[10])
			  }else if(namesup=='Agentname'){
				  setAgentDetails(ui.item[10])
			  }else if(namesup=='vendorClientName'  || namesup=='advPartyName' || namesup=='supplierName'){
				  if(chkId=="assupmst_kid"){
					  setDataOnClickForSupplier(ui.item[9])
				  }else{
				  //if(venType=='S')
					  setDataOnClickForSupplier(ui.item[10])
				  }
			  }else if(namesup=='vendorClientNameSup'){
				  setDataOnClickForSupplier(ui.item[9])
				  $('#vendorClientNameId').val(ui.item[9])
			  }else if(namesup=='vendorClientNamePayRec'){
					  setDataOnClickForSupplier(ui.item[9])
			  }
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

// GL for table in multiple line 

function getGlMulti(evt, sreachValue, voucherType, from, minorCode, fieldName, param, entryType) {
  debugger;
  var id = evt.id;
  var ctlName = evt.name;
  var tableid = $(evt).closest('table').attr('id');
  var rowIndex;
  if (tableid === "particularHeadAndAmountTbl") {
	  rowIndex = evt.parentNode.parentNode.parentNode.rowIndex;
  } else {
	  rowIndex = evt.parentNode.parentNode.rowIndex;
  }
  var trcnt = document.getElementById(tableid).rows[rowIndex];
  var namesup = evt.name;

  if (typeof minorCode == 'undefined' || minorCode == null) {
	  minorCode = "";
  }
  if (typeof voucherType == 'undefined' || voucherType == null) {
	  voucherType = "";
  }
  if (typeof param == 'undefined' || param == null) {
	  param = "";
  }
  var columns = [{ name: 'Account Head', minWidth: '400px' }, { name: 'Minor Code', minWidth: '450px' }, { name: 'Major Code', minWidth: '450px' }];
   
  if (param != "30007" && (param != '40086' || namesup == 'cItemName')) {
	  $(trcnt).find('#' + id).mcautocomplete({
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
			  $.ajax({
				  type: 'GET',
				  url: appcontext + "/newOrderForm.do?action=searchAccountHeadMultiCol&accHead=" + sreachValue + "&voucherType=" + voucherType + "&urlFrom=" + from + "&minorHead=" + minorCode + "&fieldName=" + fieldName + "&param=" + param,
				  dataType: 'json',
				  success: function (jsonData) {
					  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
				  }
			  });
		  },
		  delay: 0,
		  minLength: 0,
		  open: function () {
			  $(evt).autocomplete("widget")
				  .appendTo("#autocomplete-results")
				  .css("position", "absolute");
			  addCustomLink(trcnt, namesup, evt);
		  },
		  focus: function (event, ui) {
			  $(evt).attr("placeholder", ui.item.label);
			  return false;
		  },
		  select: function (event, ui) {
			  evt.value = (ui.item ? ui.item[0] : '');
			  // call onchange method here
			  debugger;
			  if(namesup=='itemName'  || namesup=='cItemName'){
				setDataOnClick(ui.item[4],ui.item[5],namesup);  // pass id index instead of 0 
			}else if(namesup=='supGlHeadname'){
				setGLAccHeadDetail(ui.item[4]);
			}else if(namesup=='supGlHeadnameLR'){
				setGLAccHeadDetailLR(ui.item[4]);
			}else{
				setDataOnClick(ui.item[4]); 
			}
			//   handleCustomSelect(namesup, ui);
			  return false;
		  }
	  });

	  var inputBoxPosition = $(evt).offset();
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
	  });
  } else {
	  if (ctlName === "cItemName") {
		  entryType = "";
	  }
	  $(trcnt).find('#' + id).mcautocomplete({
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
			  $.ajax({
				  type: 'GET',
				  url: appcontext + "/newOrderForm.do?action=searchAccountHeadMultiCol&accHead=" + sreachValue + "&voucherType=" + voucherType + "&urlFrom=" + from + "&minorHead=" + minorCode + "&fieldName=" + fieldName + "&param=" + param + "&entryType=" + entryType,
				  dataType: 'json',
				  success: function (jsonData) {
					  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
				  }
			  });
		  },
		  delay: 0,
		  minLength: 2,
		  open: function () {
			  addCustomLink(trcnt, namesup, evt);
			  $(evt).autocomplete("widget")
				  .appendTo("#autocomplete-results")
				  .css("position", "absolute");
		  },
		  focus: function (event, ui) {
			  $(evt).attr("placeholder", ui.item.label);
			  return false;
		  },
		  
		  select: function (event, ui) {
			  evt.value = (ui.item ? ui.item[0] : '');
			  // handleCustomSelect(namesup, ui);
			  debugger;
			  
			  if(namesup=='itemName'  || namesup=='cItemName'){
				  if (!ui.item) {
					  // Handle the "Create GL" link
					  var createGLItem = {
						  value: "/CobaSys/HeadCreation.do?action=fetchRecord&param=40010", // Set the value to your target URL
						  label: "Create GL", // Set the label for display
						  // Add any other properties that match your data structure
						  // For example, you might need to set an 'id' or 'class' property
					  };
			  
					  // Use the manually created item to perform actions
					  var selectedItem = createGLItem;
			  
					  // Access the properties of the selected item
					  var itemValue = selectedItem.value; // The item's value
					  var itemLabel = selectedItem.label; // The item's label
					  // Add any other properties as needed
			  
					  // Use the properties to perform actions, such as redirection
					  window.location.href = itemValue; // Redirect to the specified URL
				  }
				  setDataOnClick(ui.item[4],ui.item[5],namesup);  // pass id index instead of 0 
			  }else{
				  
				  setDataOnClick(ui.item[4]); 
			  }
			  // Check if the selected item is the "Create GL" row
			  
			  return false;
		  },
		  
	  });
	  
	  var inputBoxPosition = $(evt).offset();
  
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
	  });
  }
}


// GL for single control 
function getGlMultiForSingleControl(evt,sreachValue,voucherType,from,minorCode,fieldName,param){
   var id = evt.id;
   var namesup = evt.name;
  if(typeof minorCode == 'undefined' || minorCode == null){
	  minorCode="";
  }
  if(typeof voucherType == 'undefined' || voucherType == null){
	  voucherType="";
  }
  if(typeof param == 'undefined' || param == null){
	  param="";
  }
  var columns = [{name: 'Account Head', minWidth: '400px'}, {name: 'Minor Code ', minWidth:'450px'},{name: 'Major Code', minWidth: '450px'}];
  $('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newOrderForm.do?action=searchAccountHeadMultiCol&accHead="+sreachValue+"&voucherType="+voucherType+"&urlFrom="+from +"&minorHead="+minorCode + "&fieldName="+fieldName+"&param="+param,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
		 open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	   focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
		}, 
	 
		 select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  //call onchange method here
			  debugger;
			  if(namesup=='itemName'  || namesup=='cItemName'){
				  setDataOnClick(ui.item[4]);  // pass id index instead of 0 
			  }else if(namesup=='bedgethead'){
				  setDataOnClickbhead(ui.item[4]);
			  }
			  else{
				  setDataOnClick(ui.item[4],namesup); 
			  }
			  
			  return false;
		  }
   });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	 });
}

// item for table in multiple line 
function getItemMultiForRows(evt,supid,menuNext,isItemCode,group,subcategory){
   var id = evt.id;
   var sreachValue = evt.value;
   var tableid = $(evt).closest('table').attr('id')
   var rowIndex;
   rowIndex = evt.parentNode.parentNode.rowIndex;
   
   var trcnt = document.getElementById(tableid).rows[rowIndex]
   var namesup = evt.name;
  if(typeof supid == 'undefined' || supid == null){
	  supid="-1";
  }
  if(typeof isItemCode == 'undefined' || isItemCode == null){
	  isItemCode="item";
  }
  if(typeof menuNext == 'undefined' || menuNext == null){
	  menuNext="";
  }
  if(typeof group == 'undefined' || group == null){
	  group="0";
  }
  if(typeof subcategory == 'undefined' || subcategory == null){
	  subcategory="0";
  }
  var columns = [{name: 'Item Name', minWidth: '300px'}, {name: 'Item Code ', minWidth:'150px'},{name: 'HSN Code', minWidth: '150px'},{name: 'Sub-Category', minWidth: '250px'},{name: 'Sub-Group', minWidth: '250px'}];
  $(trcnt).find('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
		   type: 'GET',
		  url: appcontext+"/newOrderForm.do?action=sreachItemNameMultiCol&isItemCode="+isItemCode+"&supId="+supid+"&param="+menuNext+"&itemName="+sreachValue+"&group="+group+"&subcategory="+subcategory,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  //call onchange method here
			  debugger;
			  if(evt.id=="itemcode"){
				  setDataOnClick(ui.item[5],evt); 
			  }else if(evt.id=="poItemCode"){
				setDataOnClickUs(ui.item[5]); 
			  }
			  else{
			  setDataOnClick(ui.item[5]);  
			  }
			  
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

// item for table in Single text box
function getItemMultiForSingleTextBox(evt,supid,menuNext,isItemCode,group,subcategory){
   var id = evt.id;
   var sreachValue = evt.value;
  
   var namesup = evt.name;
  if(typeof supid == 'undefined' || supid == null){
	  supid="-1";
  }
  if(typeof isItemCode == 'undefined' || isItemCode == null){
	  isItemCode="item";
  }
  if(typeof menuNext == 'undefined' || menuNext == null){
	  menuNext="";
  }
  if(typeof group == 'undefined' || group == null){
	  group="0";
  }
  if(typeof subcategory == 'undefined' || subcategory == null){
	  subcategory="0";
  }
  var columns = [{name: 'Item Name', minWidth: '300px'}, {name: 'Item Code ', minWidth:'150px'},{name: 'HSN Code', minWidth: '150px'},{name: 'Sub-Category', minWidth: '250px'},{name: 'Sub-Group', minWidth: '250px'}];
  $('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
		   type: 'GET',
		  url: appcontext+"/newOrderForm.do?action=sreachItemNameMultiCol&isItemCode="+isItemCode+"&supId="+supid+"&param="+menuNext+"&itemName="+sreachValue+"&group="+group+"&subcategory="+subcategory,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  //call onchange method here
			  debugger;
			  if(namesup=="itemChk"){
				  $('#itemname').val(ui.item[5])
			  }else if(namesup=="service"){
				  setDataOnClickService(ui.item[5])
			  }
			  
			  
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

function getItemServicesMultiCol(evt,searchValue,param){
  debugger;
  var id = evt.id;
  var tableid = $(evt).closest('table').attr('id')
  var rowIndex;
   
  rowIndex = evt.parentNode.parentNode.rowIndex;
   
  var trcnt = document.getElementById(tableid).rows[rowIndex]
  var namesup = evt.name;
  
  var columns = [{name: 'Item/Service', minWidth: '200px'}, {name: 'Item/Service Code ', minWidth:'200px'},{name: 'SubCategory', minWidth: '200px'},{name: 'SubGroup', minWidth: '200px'}];
  $(trcnt).find('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newOrderForm.do?action=searchItemServiceMultiCol&searchItemService="+searchValue+"&param="+param,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 2,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  //call onchange method here
			  debugger;
			  
			  setDataOnClickForItemService(ui.item[5]);  // pass id index instead of 5 
			  
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

function getMultiSearchBranchCode(evt,searchValue,param){
  debugger;
  var id = evt.id;
  var seacrValue=evt.value;

  var columns = [{name: 'Branch Code', minWidth: '150px'}, {name: 'Branch Name', minWidth:'350px'},{name: 'Directorate', minWidth:'160px'},
  {name: 'Zone', minWidth:'260px'},{name: 'C-Office', minWidth:'260px'}];
  $('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newOrderForm.do?action=getMultiSearchBranchCode&branchCode="+request.term+"&param="+param,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]+"-"+ui.item[1]: '');
			  //call onchange method here
			  debugger;
			  if(typeof param != 'undefined' && param != null && param=="40256")
			  {
				  setbofficeOnClick(ui.item[0],ui.item[5]);
			  }else{
			  $("#secOptions").val(ui.item[0]);
			   if(param=="40228")
			  {
			   setBranchCode(ui.item[0]);
			  }  

			  getPBLevelDetails(ui.item[0]);
			  }
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

function getMultiSearchBranchForTable(evt,searchValue,param){
  debugger;
  var id = evt.id;
  var sVal=evt.value;
  var tableid = $(evt).closest('table').attr('id')
  var rowIndex;
   
  rowIndex = evt.parentNode.parentNode.rowIndex;
   
  var trcnt = document.getElementById(tableid).rows[rowIndex]
  var namesup = evt.name;

  if(clientName==="PB"){
	  var columns = [{name: 'Office Code', minWidth: '150px'}, {name: 'Office Name', minWidth:'350px'},{name: 'Vertical', minWidth:'160px'},
	  {name: 'Zone', minWidth:'260px'},{name: 'DDO', minWidth:'260px'}]; 
  }else{
	  var columns = [{name: 'Branch Code', minWidth: '150px'}, {name: 'Branch Name', minWidth:'350px'},{name: 'Directorate', minWidth:'160px'},
	  {name: 'Zone', minWidth:'260px'},{name: 'C-Office', minWidth:'260px'}];
  }
  $(trcnt).find('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newOrderForm.do?action=getMultiSearchBranchCode&branchCode="+sVal+"&param="+param,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[1]: '');
			  //call onchange method here
			  debugger;
			  setBranchName(ui.item[0],ui.item[1]);
			   
			  
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}


function getMultiBillingsType(evt,sType){
  debugger;
  var id = evt.id;
  var sVal=evt.value;
  var tableid = $(evt).closest('table').attr('id')
  var rowIndex;
   
  rowIndex = evt.parentNode.parentNode.rowIndex;
   
  var trcnt = document.getElementById(tableid).rows[rowIndex]
  var namesup = evt.name;
  
  var columns = [{name: 'Billings Name', minWidth: '200px'},{name: 'Code', minWidth: '50px'}];
  $(trcnt).find('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newBatsMapping.do?action=getMultiSearchBatsData&sVal="+sVal+"&sType="+sType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 2,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[1]+"-"+ui.item[0]: '');
			  //call onchange method here
			  debugger;
			  
			  setBillingsType(ui.item[1]);
				// pass id index instead of 0 
			  
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

function getMultiChannelName(evt,sType){
  debugger;
  var id = evt.id;
  var sVal=evt.value;
  var tableid = $(evt).closest('table').attr('id')
  var rowIndex;
   
  rowIndex = evt.parentNode.parentNode.rowIndex;
   
  var trcnt = document.getElementById(tableid).rows[rowIndex]
  var namesup = evt.name;
  
  var columns = [{name: 'Channel', minWidth: '300px'},{name: 'Code', minWidth: '150px'}];
  $(trcnt).find('#'+id ).mcautocomplete({
  
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newBatsMapping.do?action=getMultiSearchBatsData&sVal="+sVal+"&sType="+sType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  //call onchange method here
			  debugger;
			  
			   setChannelName(ui.item[0]); // pass id index instead of 0 
			  
			  return false;
		  }
	  }); var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

function getMultiBatsType(evt,sType){
  debugger;
  var id = evt.id;
  var sVal=evt.value;
  var tableid = $(evt).closest('table').attr('id')
  var rowIndex;
   
  rowIndex = evt.parentNode.parentNode.rowIndex;
   
  var trcnt = document.getElementById(tableid).rows[rowIndex]
  var namesup = evt.name;
  
  var columns = [{name: 'Bats Name', minWidth: '300px'}];
  $(trcnt).find('#'+id ).mcautocomplete({
  
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newBatsMapping.do?action=getMultiSearchBatsData&sVal="+sVal+"&sType="+sType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 2,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  //call onchange method here
			  debugger;
			  
			  setBatsName(ui.item[0]);  // pass id index instead of 0 
			  
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

function getMultiAgencyData(evt,sType){
  debugger;
  var id = evt.id;
  var sVal=evt.value;
  var tableid = $(evt).closest('table').attr('id')
  var rowIndex;
   
  rowIndex = evt.parentNode.parentNode.rowIndex;
   
  var trcnt = document.getElementById(tableid).rows[rowIndex]
  var namesup = evt.name;
  
  var columns = [{name: 'Agency Type', minWidth: '300px'},{name: 'Agency Name', minWidth: '300px'}];
  $(trcnt).find('#'+id ).mcautocomplete({
  
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newBatsMapping.do?action=getMultiSearchBatsData&sVal="+sVal+"&sType="+sType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]+"-"+ui.item[1]: '');
			  //call onchange method here
			  debugger;
			  
			  setAgencyType(ui.item[0]);  // pass id index instead of 0 
			  
			  return false;
		  }
	  });
}

function getMultiGLHeadForm(evt,sType){
  debugger;
  var id = evt.id;
  var sVal=evt.value;
  var tableid = $(evt).closest('table').attr('id')
  var rowIndex;
   
  rowIndex = evt.parentNode.parentNode.rowIndex;
   
  var trcnt = document.getElementById(tableid).rows[rowIndex]
  var namesup = evt.name;
  
  var columns = [{name: 'Debitor Code', minWidth: '300px'},{name: 'Debitor Name', minWidth: '300px'}];
  $(trcnt).find('#'+id ).mcautocomplete({
  
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newBatsMapping.do?action=getMultiSearchBatsData&sVal="+sVal+"&sType="+sType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 2,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[1]: '');
			  //call onchange method here
			  debugger;
			  
			  setMultiGLHeadForm(ui.item[0]);  // pass id index instead of 0 
			  
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}

function getLDInvDtl(evt,billType,chkId){
  debugger;
   var id = evt.id;
   var namesup = evt.name;
  if(typeof billType == 'undefined' || billType == null){
	  billType="";
  }
  if(typeof chkId == 'undefined' || chkId == null){
	  chkId="supgst_kid";
  }
  var columns = [{name: 'Supplier Code/Name', minWidth: '350px'},{name: 'Sales Off Code', minWidth: '190px'}, {name: 'GST Number ', minWidth:'100px'},{name: 'PAN Number', minWidth: '100px'},{name: 'Address', minWidth: '200px'},{name: 'City', minWidth: '100px'},{name: 'State', minWidth: '120px'}];
  $('#'+id ).mcautocomplete({
  
  //$("#submitby").mcautocomplete({
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',
			url: appcontext+"/newOrderForm.do?action=getPurchaseInvoiceCustomerForLD&accHead="+request.term+"&billType="+billType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 /* select: function (event, ui) {
		  event.preventDefault();
		  var url = ui.item.value;
		  if (url != '#') {
			  location.href = url;
		  }
	  }*/
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  setDataOnClickForSupplier(ui.item[10])
			  getInvoiceDetails(ui.item[10],'1');
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}


function getMultiSearchBranchCodeForOffice(evt,searchValue,param,dir,zoneval,stateval,cityval,contOff){
  debugger;
  var id = evt.id;
  var seacrValue=evt.value;

  var columns = [{name: 'Branch Code', minWidth: '150px'}, {name: 'Branch Name', minWidth:'350px'},{name: 'Directorate', minWidth:'160px'},
  {name: 'Zone', minWidth:'260px'},{name: 'C-Office', minWidth:'260px'}];
  $('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',

			url: appcontext+"/newOrderForm.do?action=getMultiBranchCodeOffWise&branchCode="+request.term+"&param="+param+"&dir="+dir+"&zoneval="+zoneval+"&stateval="+stateval+"&cityval="+cityval+"&contOff="+contOff,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 0,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]+"-"+ui.item[1]: '');
			  //call onchange method here
			  debugger;
			  $("#secOptions").val(ui.item[0]);
			   if(param=="40228" || param=="33023"){
				 setBranchCode(ui.item[0]);
				}
			  getPBLevelDetails('secOptions');
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
}


function getGlMultiJournal(evt,sreachValue,voucherType,from,minorCode,fieldName,param,entryType){
   var id = evt.id;
   var ctlName=evt.name;
   var tableid = $(evt).closest('table').attr('id')
   var rowIndex;
   if(tableid==="particularHeadAndAmountTbl"){
	   rowIndex = evt.parentNode.parentNode.parentNode.rowIndex;
   }else{
	   rowIndex = evt.parentNode.parentNode.rowIndex;
   }
   var trcnt = document.getElementById(tableid).rows[rowIndex]
   var namesup = evt.name;
  if(typeof minorCode == 'undefined' || minorCode == null){
	  minorCode="";
  }
  if(typeof voucherType == 'undefined' || voucherType == null){
	  voucherType="";
  }
  if(typeof param == 'undefined' || param == null){
	  param="";
  }
  var columns = [{name: 'Account Head', minWidth: '400px'}, {name: 'Minor Code ', minWidth:'450px'},{name: 'Major Code', minWidth: '450px'}];

  
  $(trcnt).find('#'+id ).mcautocomplete({
  
  
	   
		  showHeader: true,
		  columns: columns,
		  source: function (request, response) {
		  $.ajax({
			  type: 'GET',
			url: appcontext+"/newOrderForm.do?action=searchAccountHeadMultiColJournal&accHead="+sreachValue+"&voucherType="+voucherType+"&urlFrom="+from +"&minorHead="+minorCode + "&fieldName="+fieldName+"&param="+param+"&entryType="+entryType,
			dataType: 'json',
			  success: function(jsonData) {
				  response(jsonData[0].map(function (_, c) { return jsonData.map(function (r) { return r[c]; }); }));
			  }
		  });
	  },
	  delay: 0,
	  minLength: 2,
	  open: function() {
		  $(evt).autocomplete("widget")
				 .appendTo("#autocomplete-results")
				 .css("position", "absolute")},
	  focus: function( event, ui ) {
		$(evt).attr("placeholder", ui.item.label);
		return false;  
	  }, 
	 
	  select: function(event, ui) {
			  evt.value = (ui.item ? ui.item[0]: '');
			  //call onchange method here
			  debugger;
			  if(evt.id=="itemcode"){
				  setDataOnClick(ui.item[4],evt); 
			  }
			  else{
			  setDataOnClick(ui.item[4]); 
			  } 
			  
			  
			  return false;
		  }
	  });
	  var inputBoxPosition =  $(evt).offset();
	  // var inputBoxPositionY = evt.offsetY;

	  // Set the position of the autocomplete window
	  $(".ui-autocomplete").css({
		  top: inputBoxPosition.top + $(evt).outerHeight(),
		  left: inputBoxPosition.left
		  // display: "block"
	  });
  
}
