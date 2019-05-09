apiBoard = (function () {
  var $ = function (id) { return document.getElementById(id) };

  //Initialize Canvas
  var canvas = this.__canvas = new fabric.Canvas('c', {
    isDrawingMode: true,
    height: 500
  });

  fabric.Object.prototype.transparentCorners = false;

  var drawingModeEl = $('drawing-mode'),
    drawingOptionsEl = $('drawing-mode-options'),
    drawingColorEl = $('drawing-color'),
    drawingLineWidthEl = $('drawing-line-width'),
    clearEl = $('clear-canvas'),
    drawingMoveEl = $('drawing-move'),
    drawTriangleEl = $('drawing-triangle'),
    drawSquareEl = $('drawing-square'),
    drawCircleEl = $('drawing-circle');

  clearEl.onclick = function () { canvas.clear() };

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
      canvas.add(shp).setActiveObject(shp);
      canvas.shape = null;
    }
  });

  var Socekt = function (f) {
    canvas.on('mouse:up', f);
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

  return {
    getJson: getJson,
    loadJson: loadJson,
    reSize: reSize,
    Socket: Socekt
  }


})();

window.onload = function () {
  $("canvas").css("width", "100%");
  apiBoard.reSize($("#boardContainer").width());
};

$(window).resize(function () {
  apiBoard.reSize($("#boardContainer").width());
});
