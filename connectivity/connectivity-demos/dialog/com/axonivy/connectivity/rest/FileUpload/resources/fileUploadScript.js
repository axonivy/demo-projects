function uploadFileJS(file) {
  var form = new FormData();
  var filename = file[0].name;

  form.append("file", file[0]);

  var settings = {
    "async": true,
    "crossDomain": true,
    "url": "http://" + window.location.hostname + ":" + window.location.port + "/designer/api/file",
    "method": "PUT",
    "headers": { "X-Requested-By": "ivy" },
    "processData": false,
    "contentType": false,
    "mimeType": "multipart/form-data",
    "data": form
  };

  return $.ajax(settings).done(function (response) {
    console.log(response);
    addToTable(filename);

    jsf.ajax.request(this, event, {
      render: 'form:dataTable ',
      'CLIENT_BEHAVIOR_RENDERING_MODE': 'UNOBSTRUSIVE'
    });
  });
}

var logic = {
  addJsToList: function (data, callback) {
    $.ajax({
      url: "rest/method/addJsToList",
      type: "POST",
      dataType: 'json',
      data: JSON.stringify(data),
      success: function (data) {
        if (data.ivyRedirectUrl) {
          window.location.replace(data.ivyRedirectUrl);
        } else {
          return callback(data);
        }
      }
    }).error(function () { alert("Server error when calling method addJsToList"); });
  },
  loaded: true
};

function addToTable(fileName) {
  logic.addJsToList(
    { "fileName": fileName },
    function (returnData) { });
};
