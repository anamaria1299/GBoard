apiBoard = (function () {
  var $$ = function (id) { return document.getElementById(id) };
  var SocketFunction = null;

  //Initialize Canvas
  var canvas = this.__canvas = new fabric.Canvas('c', {
    isDrawingMode: true,
    height: 500
  });

  fabric.Object.prototype.transparentCorners = false;

  var drawingModeEl = $$('drawing-mode'),
    drawingOptionsEl = $$('drawing-mode-options'),
    drawingColorEl = $$('drawing-color'),
    drawingLineWidthEl = $$('drawing-line-width'),
    clearEl = $$('clear-canvas'),
    drawingMoveEl = $$('drawing-move'),
    drawTriangleEl = $$('drawing-triangle'),
    drawSquareEl = $$('drawing-square'),
    drawCircleEl = $$('drawing-circle'),
    drawTextEl = $$('drawing-text'),
    drawImageEl = $$('drawing-image'),
    drawUpload = $$('drawing-upload'),
    drawDownload = $$('drawig-download');

  clearEl.onclick = function () { 
    canvas.clear() 
    SocketFunction();
  };

  drawingModeEl.onclick = function () {
    canvas.shape = null;
    canvas.isDrawingMode = true;
    drawingOptionsEl.style.display = '';
  };

  drawingMoveEl.onclick = function () {
    canvas.shape = null;
    canvas.isDrawingMode = false;
    drawingOptionsEl.style.display = 'none';
  }

  drawTriangleEl.onclick = function () {
    canvas.isDrawingMode = false;
    canvas.shape = function (x, y) {
      return new fabric.Triangle({ width: 30, height: 30, fill: drawingColorEl.value, left: x, top: y });
    }
  }

  drawCircleEl.onclick = function () {
    canvas.isDrawingMode = false;
    canvas.shape = function (x, y) {
      return new fabric.Circle({ radius: 20, fill: drawingColorEl.value, left: x, top: y });
    }
  }

  drawSquareEl.onclick = function () {
    canvas.isDrawingMode = false;
    canvas.shape = function (x, y) {
      return new fabric.Rect({ width: 30, height: 30, fill: drawingColorEl.value, left: x, top: y });
    }
  }

  drawTextEl.onclick = function(){
    canvas.isDrawingMode = false;
    canvas.shape = function(x,y){
      return new fabric.IText("I'm in Comic Sans", {
        fontFamily: 'Comic Sans',
        left :x,
        top : y
      });
    }
  }

  drawImageEl.onclick = function () {
    canvas.isDrawingMode = false;
    if($("#contentImg")) $("#contentImg").remove();
    $("body").append('<div id="contentImg" style="display:none"><input type="file" id="inputImg"><img src="" id="my-image" height="200" alt="Image preview..."></div>')
    $("#inputImg").click();

    $('#inputImg').change(function (e) {
      var imgElement = document.querySelector('img');
      var file = e.target.files[0];
      var reader = new FileReader();
      reader.onloadend = function () {
        imgElement.src = reader.result;
        var img = $$('my-image');
        canvas.add(new fabric.Image(img, { left: 0, top: 0, scaleX: 0.20 ,scaleY: 0.20 }));
      }

      if (file) {
        reader.readAsDataURL(file); //reads the data as a URL
      } else {
        imgElement.src = "";
      }
    });


  }

  drawUpload.onclick = function(){
    upload();
  }

  drawDownload.onclick = function(){
    download();
  }



  if (fabric.PatternBrush) {
    canvas.freeDrawingBrush = new fabric['PencilBrush'](canvas);
    canvas.freeDrawingBrush.color = drawingColorEl.value;
    canvas.freeDrawingBrush.width = parseInt(drawingLineWidthEl.value, 10) || 1;

  }

  drawingColorEl.onchange = function () {
    canvas.freeDrawingBrush.color = this.value;
  };

  drawingLineWidthEl.onchange = function () {
    canvas.freeDrawingBrush.width = parseInt(this.value, 10) || 1;
    this.previousSibling.innerHTML = this.value;
  };

  if (canvas.freeDrawingBrush) {
    canvas.freeDrawingBrush.color = drawingColorEl.value;
    canvas.freeDrawingBrush.width = parseInt(drawingLineWidthEl.value, 10) || 1;
  }

  canvas.on('mouse:down', function (options) {
    if (canvas.shape) {
      console.log(options.e.clientY, options.e.clientX);
      var shp = canvas.shape(options.e.clientX - 15, options.e.clientY - 115);
      if (shp) {
        canvas.add(shp).setActiveObject(shp);
        canvas.shape = null;
      }
    }
  });

  var Socekt = function (f) {
    SocketFunction =f 
    canvas.on('mouse:up', SocketFunction);
  }

  //public methods
  var getJson = function () {
    return JSON.stringify(canvas);
  }

  var loadJson = function (data) {
    canvas.loadFromJSON(data);
  }

  var reSize = function (width) {
    canvas.setWidth(width);
    canvas.calcOffset();
  }

  var download = function(){
		var file = $("<a />", {
		    "download": "data.json",
		    "href" : "data:application/json;charset=utf-8," + encodeURIComponent(getJson()),
		  });
		file[0].click();
	}
	
	var upload = function(){
		$("body").append('<input id="inputUpload" type="file" style="display:none">');
		$("#inputUpload").click();
		var fr = new FileReader();
		  
		  fr.onload = function(e) { 
		    var result = JSON.parse(e.target.result);
		    var formatted = JSON.stringify(result, null, 2);
        loadJson(formatted);
        SocketFunction();
		    $("#inputUpload").remove();
		  }
		
		$("#inputUpload").change(function(){
			fr.readAsText($("#inputUpload").prop('files').item(0));
    });
  }
		 

  return {
    getJson: getJson,
    loadJson: loadJson,
    reSize: reSize,
    Socket: Socekt
  }


})();

$(document).ready(function(){
  $("canvas").css("width", "100%");
  apiBoard.reSize($("#boardContainer").width());
});


$(window).resize(function () {
  apiBoard.reSize($("#boardContainer").width());
});
