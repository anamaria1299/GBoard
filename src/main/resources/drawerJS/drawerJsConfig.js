var drawerPlugins = [
    'Pencil',
    'Eraser',
    'Text',
    'Color',
    'TextLineHeight',
    'TextAlign',
    'TextFontFamily',
    'TextFontSize',
    'TextFontWeight',
    'TextFontStyle',
    'TextDecoration',
    'TextColor',
    'TextBackgroundColor',
    'Line',
    'Triangle',
    'Rectangle',
    'Circle',
    'Polygon',
    'Color',
    'ShapeBorder',
    'BrushSize',
    'Resize',
    'ShapeContextMenu',
    'Image'
];

var drawerPluginsConfig = {
    Text: {
        fonts: {
            'Georgia': 'Georgia, serif',
            'Palatino': "'Palatino Linotype', 'Book Antiqua', Palatino, serif",
            'Times New Roman': "'Times New Roman', Times, serif",
            'Arial': 'Arial, Helvetica, sans-serif',
            'Arial Black': "'Arial Black', Gadget, sans-serif",
            'Comic Sans MS': "'Comic Sans MS', cursive, sans-serif",
            'Impact': 'Impact, Charcoal, sans-serif',
            'Lucida Grande': "'Lucida Sans Unicode', 'Lucida Grande', sans-serif",
            'Tahoma': 'Tahoma, Geneva, sans-serif',
            'Trebuchet MS': "'Trebuchet MS', Helvetica, sans-serif",
            'Verdana': 'Verdana, Geneva, sans-serif',
            'Courier New': "'Courier New', Courier, monospace",
            'Lucida Console': "'Lucida Console', Monaco, monospace"
        },
        defaultFont: 'Palatino',
        editIconMode : false,
        editIconSize : 'small',
        defaultValues: {
            fontSize: 72,
            lineHeight: 2,
            textFontWeight: 'bold'
        },
        predefined: {
            fontSize: [8, 12, 14, 16, 32, 40, 72],
            lineHeight: [1, 2, 3, 4, 6]
       }
    },
    Image: {
        scaleDownLargeImage: true,
        maxImageSizeKb: 5120,
        acceptedMIMETypes: ['image/jpeg', 'image/png', 'image/gif'],
        cropIsActive: true
    }
}
