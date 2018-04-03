(function(factory) {
    "use strict";
    if (typeof define === 'function' && define.amd) { // AMD
        define(['jquery'], factory);
    } else if (typeof exports == "object" && typeof module == "object") { // CommonJS
        module.exports = factory(require('jquery'));
    } else { // Browser
        factory(jQuery);
    }
})(function($, undefined) {
    "use strict";
    var idName = 'material-design-iconic-font-box',
        datas = [{ "category": "应用程序", "fonts": ["3d-rotation", "airplane-off", "airplane", "album", "archive", "assignment-account", "assignment-alert", "assignment-check", "assignment-o", "assignment-return", "assignment-returned", "assignment", "attachment-alt", "attachment", "audio", "badge-check", "balance-wallet", "balance", "battery-alert", "battery-flash", "battery-unknown", "battery", "bike", "block-alt", "block", "boat", "book-image", "book", "bookmark-outline", "bookmark", "brush", "bug", "bus", "cake", "car-taxi", "car-wash", "car", "card-giftcard", "card-membership", "card-travel", "card", "case-check", "case-download", "case-play", "case", "cast-connected", "cast", "chart-donut", "chart", "city-alt", "city", "close-circle-o", "close-circle", "close", "cocktail", "code-setting", "code-smartphone", "code", "coffee", "collection-bookmark", "collection-case-play", "collection-folder-image", "collection-image-o", "collection-image", "collection-item-1", "collection-item-2", "collection-item-3", "collection-item-4", "collection-item-5", "collection-item-6", "collection-item-7", "collection-item-8", "collection-item-9-plus", "collection-item-9", "collection-item", "collection-music", "collection-pdf", "collection-plus", "collection-speaker", "collection-text", "collection-video", "compass", "cutlery", "delete", "dialpad", "dns", "drink", "edit", "email-open", "email", "eye-off", "eye", "eyedropper", "favorite-outline", "favorite", "filter-list", "fire", "flag", "flare", "flash-auto", "flash-off", "flash", "flip", "flower-alt", "flower", "font", "fullscreen-alt", "fullscreen-exit", "fullscreen", "functions", "gas-station", "gesture", "globe-alt", "globe-lock", "globe", "graduation-cap", "group", "home", "hospital-alt", "hospital", "hotel", "hourglass-alt", "hourglass-outline", "hourglass", "http", "image-alt", "image-o", "image", "inbox", "invert-colors-off", "invert-colors", "key", "label-alt-outline", "label-alt", "label-heart", "label", "labels", "lamp", "landscape", "layers-off", "layers", "library", "link", "lock-open", "lock-outline", "lock", "mail-reply-all", "mail-reply", "mail-send", "mall", "map", "menu", "money-box", "money-off", "money", "more-vert", "more", "movie-alt", "movie", "nature-people", "nature", "navigation", "open-in-browser", "open-in-new", "palette", "parking", "pin-account", "pin-assistant", "pin-drop", "pin-help", "pin-off", "pin", "pizza", "plaster", "power-setting", "power", "print", "puzzle-piece", "quote", "railway", "receipt", "refresh-alt", "refresh-sync-alert", "refresh-sync-off", "refresh-sync", "refresh", "roller", "ruler", "scissors", "screen-rotation-lock", "screen-rotation", "search-for", "search-in-file", "search-in-page", "search-replace", "search", "seat", "settings-square", "settings", "shape", "shield-check", "shield-security", "shopping-basket", "shopping-cart-plus", "shopping-cart", "sign-in", "sort-amount-asc", "sort-amount-desc", "sort-asc", "sort-desc", "spellcheck", "spinner", "storage", "store-24", "store", "subway", "sun", "tab-unselected", "tab", "tag-close", "tag-more", "tag", "thumb-down", "thumb-up-down", "thumb-up", "ticket-star", "toll", "toys", "traffic", "translate", "triangle-down", "triangle-up", "truck", "turning-sign", "ungroup", "wallpaper", "washing-machine", "window-maximize", "window-minimize", "window-restore", "wrench", "zoom-in", "zoom-out"] }, { "category": "通知", "fonts": ["alert-circle-o", "alert-circle", "alert-octagon", "alert-polygon", "alert-triangle", "help-outline", "help", "info-outline", "info", "notifications-active", "notifications-add", "notifications-none", "notifications-off", "notifications-paused", "notifications"] }, { "category": "人", "fonts": ["account-add", "account-box-mail", "account-box-o", "account-box-phone", "account-box", "account-calendar", "account-circle", "account-o", "account", "accounts-add", "accounts-alt", "accounts-list-alt", "accounts-list", "accounts-outline", "accounts", "face", "female", "male-alt", "male-female", "male", "mood-bad", "mood", "run", "walk"] }, { "category": "文件", "fonts": ["cloud-box", "cloud-circle", "cloud-done", "cloud-download", "cloud-off", "cloud-outline-alt", "cloud-outline", "cloud-upload", "cloud", "download", "file-plus", "file-text", "file", "folder-outline", "folder-person", "folder-star-alt", "folder-star", "folder", "gif", "upload"] }, { "category": "编辑", "fonts": ["border-all", "border-bottom", "border-clear", "border-color", "border-horizontal", "border-inner", "border-left", "border-outer", "border-right", "border-style", "border-top", "border-vertical", "copy", "crop", "format-align-center", "format-align-justify", "format-align-left", "format-align-right", "format-bold", "format-clear-all", "format-clear", "format-color-fill", "format-color-reset", "format-color-text", "format-indent-decrease", "format-indent-increase", "format-italic", "format-line-spacing", "format-list-bulleted", "format-list-numbered", "format-ltr", "format-rtl", "format-size", "format-strikethrough-s", "format-strikethrough", "format-subject", "format-underlined", "format-valign-bottom", "format-valign-center", "format-valign-top", "redo", "select-all", "space-bar", "text-format", "transform", "undo", "wrap-text"] }, { "category": "评论", "fonts": ["comment-alert", "comment-alt-text", "comment-alt", "comment-edit", "comment-image", "comment-list", "comment-more", "comment-outline", "comment-text-alt", "comment-text", "comment-video", "comment", "comments"] }, { "category": "表单", "fonts": ["check-all", "check-circle-u", "check-circle", "check-square", "check", "circle-o", "circle", "dot-circle-alt", "dot-circle", "minus-circle-outline", "minus-circle", "minus-square", "minus", "plus-circle-o-duplicate", "plus-circle-o", "plus-circle", "plus-square", "plus", "square-o", "star-circle", "star-half", "star-outline", "star"] }, { "category": "硬件", "fonts": ["bluetooth-connected", "bluetooth-off", "bluetooth-search", "bluetooth-setting", "bluetooth", "camera-add", "camera-alt", "camera-bw", "camera-front", "camera-mic", "camera-party-mode", "camera-rear", "camera-roll", "camera-switch", "camera", "card-alert", "card-off", "card-sd", "card-sim", "desktop-mac", "desktop-windows", "device-hub", "devices-off", "devices", "dock", "floppy", "gamepad", "gps-dot", "gps-off", "gps", "headset-mic", "headset", "input-antenna", "input-composite", "input-hdmi", "input-power", "input-svideo", "keyboard-hide", "keyboard", "laptop-chromebook", "laptop-mac", "laptop", "mic-off", "mic-outline", "mic-setting", "mic", "mouse", "network-alert", "network-locked", "network-off", "network-outline", "network-setting", "network", "phone-bluetooth", "phone-end", "phone-forwarded", "phone-in-talk", "phone-locked", "phone-missed", "phone-msg", "phone-paused", "phone-ring", "phone-setting", "phone-sip", "phone", "portable-wifi-changes", "portable-wifi-off", "portable-wifi", "radio", "reader", "remote-control-alt", "remote-control", "router", "scanner", "smartphone-android", "smartphone-download", "smartphone-erase", "smartphone-info", "smartphone-iphone", "smartphone-landscape-lock", "smartphone-landscape", "smartphone-lock", "smartphone-portrait-lock", "smartphone-ring", "smartphone-setting", "smartphone-setup", "smartphone", "speaker", "tablet-android", "tablet-mac", "tablet", "tv-alt-play", "tv-list", "tv-play", "tv", "usb", "videocam-off", "videocam-switch", "videocam", "watch", "wifi-alt-2", "wifi-alt", "wifi-info", "wifi-lock", "wifi-off", "wifi-outline", "wifi"] }, { "category": "方向", "fonts": ["arrow-left-bottom", "arrow-left", "arrow-merge", "arrow-missed", "arrow-right-top", "arrow-right", "arrow-split", "arrows", "caret-down-circle", "caret-down", "caret-left-circle", "caret-left", "caret-right-circle", "caret-right", "caret-up-circle", "caret-up", "chevron-down", "chevron-left", "chevron-right", "chevron-up", "forward", "long-arrow-down", "long-arrow-left", "long-arrow-return", "long-arrow-right", "long-arrow-tab", "long-arrow-up", "rotate-ccw", "rotate-cw", "rotate-left", "rotate-right", "square-down", "square-right", "swap-alt", "swap-vertical-circle", "swap-vertical", "swap", "trending-down", "trending-flat", "trending-up", "unfold-less", "unfold-more"] }, { "category": "标志", "fonts": ["directions-bike", "directions-boat", "directions-bus", "directions-car", "directions-railway", "directions-run", "directions-subway", "directions-walk", "directions", "layers-off", "layers", "local-activity", "local-airport", "local-atm", "local-bar", "local-cafe", "local-car-wash", "local-convenience-store", "local-dining", "local-drink", "local-florist", "local-gas-station", "local-grocery-store", "local-hospital", "local-hotel", "local-laundry-service", "local-library", "local-mall", "local-movies", "local-offer", "local-parking", "local-pharmacy", "local-phone", "local-pizza", "local-activity", "local-post-office", "local-printshop", "local-see", "local-shipping", "local-store", "local-taxi", "local-wc", "map", "my-location", "nature-people", "nature", "navigation", "pin-account", "pin-assistant", "pin-drop", "pin-help", "pin-off", "pin", "traffic"] }, { "category": "视图", "fonts": ["apps", "grid-off", "grid", "view-agenda", "view-array", "view-carousel", "view-column", "view-comfy", "view-compact", "view-dashboard", "view-day", "view-headline", "view-list-alt", "view-list", "view-module", "view-quilt", "view-stream", "view-subtitles", "view-toc", "view-web", "view-week", "widgets"] }, { "category": "时间", "fonts": ["alarm-check", "alarm-off", "alarm-plus", "alarm-snooze", "alarm", "calendar-alt", "calendar-check", "calendar-close", "calendar-note", "calendar", "time-countdown", "time-interval", "time-restore-setting", "time-restore", "time", "timer-off", "timer"] }, { "category": "社交", "fonts": ["android-alt", "android", "apple", "behance", "codepen", "dribbble", "dropbox", "evernote", "facebook-box", "facebook", "github-box", "github", "google-drive", "google-earth", "google-glass", "google-maps", "google-pages", "google-play", "google-plus-bo", "google-plus", "google", "instagram", "language-css3", "language-html5", "language-javascript", "language-python-alt", "language-python", "lastfm", "linkedin-box", "paypal", "pinterest-box", "pocket", "polymer", "rss", "share", "stackoverflow", "steam-square", "steam", "twitter-box", "twitter", "vk", "wikipedia", "windows", "500px", "8tracks", "amazon", "blogger", "delicious", "disqus", "flattr", "flickr", "github-alt", "google-old", "linkedin", "odnoklassniki", "outlook", "paypal-alt", "pinterest", "playstation", "reddit", "skype", "slideshare", "soundcloud", "tumblr", "twitch", "vimeo", "whatsapp", "xbox", "yahoo", "youtube-play", "youtube"] }, { "category": "图片", "fonts": ["aspect-ratio-alt", "aspect-ratio", "blur-circular", "blur-linear", "blur-off", "blur", "brightness-2", "brightness-3", "brightness-4", "brightness-5", "brightness-6", "brightness-7", "brightness-auto", "brightness-setting", "broken-image", "center-focus-strong", "center-focus-weak", "compare", "crop-16-9", "crop-3-2", "crop-5-4", "crop-7-5", "crop-din", "crop-free", "crop-landscape", "crop-portrait", "crop-square", "exposure-alt", "exposure", "filter-b-and-w", "filter-center-focus", "filter-frames", "filter-tilt-shift", "gradient", "grain", "graphic-eq", "hdr-off", "hdr-strong", "hdr-weak", "hdr", "iridescent", "leak-off", "leak", "looks", "loupe", "panorama-horizontal", "panorama-vertical", "panorama-wide-angle", "photo-size-select-large", "photo-size-select-small", "picture-in-picture", "slideshow", "texture", "tonality", "vignette", "wb-auto"] }, { "category": "音频", "fonts": ["eject-alt", "eject", "equalizer", "fast-forward", "fast-rewind", "forward-10", "forward-30", "forward-5", "hearing", "pause-circle-outline", "pause-circle", "pause", "play-circle-outline", "play-circle", "play", "playlist-audio", "playlist-plus", "repeat-one", "repeat", "replay-10", "replay-30", "replay-5", "replay", "shuffle", "skip-next", "skip-previous", "stop", "surround-sound", "tune", "volume-down", "volume-mute", "volume-off", "volume-up"] }, { "category": "数字", "fonts": ["n-1-square", "n-2-square", "n-3-square", "n-4-square", "n-5-square", "n-6-square", "neg-1", "neg-2", "plus-1", "plus-2", "sec-10", "sec-3", "zero"] }, { "category": "其它", "fonts": ["airline-seat-flat-angled", "airline-seat-flat", "airline-seat-individual-suite", "airline-seat-legroom-extra", "airline-seat-legroom-normal", "airline-seat-legroom-reduced", "airline-seat-recline-extra", "airline-seat-recline-normal", "airplay", "closed-caption", "confirmation-number", "developer-board", "disc-full", "explicit", "flight-land", "flight-takeoff", "flip-to-back", "flip-to-front", "group-work", "hd", "hq", "markunread-mailbox", "memory", "nfc", "play-for-work", "power-input", "present-to-all", "satellite", "tap-and-play", "vibration", "voicemail"] }];
    var fonts = [],
        categorys = [],
        size = 72,
        defaultCategory = '应用程序';
    for (var i in datas) {
        if (datas[i].category == '') {
            datas[i].category = defaultCategory;
        }
        if (fonts[datas[i].category] == undefined) {
            fonts[datas[i].category] = [];
            categorys.push(datas[i].category);
        }
        fonts[datas[i].category] = datas[i].fonts;
    }
    $.fn.materialDesignIconicFont = function(target, pageSize) {
        size = pageSize ? pageSize : size;
        $(this).click(function(event) {
            event.stopPropagation();
            event.preventDefault();

            var eTop = target[0].offsetTop + target[0].offsetHeight;
            var eLeft = target[0].offsetLeft;

            if ($('#' + idName)[0]) {
                $('#' + idName).css({ top: eTop, left: eLeft }).toggle();
                return;
            }

            var box = '<div id="' + idName + '" style="top: ' + eTop + 'px; left: ' + eLeft + 'px">' +
                '<div style="float:right">' +
                '<a href="javascript:void(0);" id="mdif-close-font"><i class="zmdi zmdi-close"></i></a>' +
                '</div>' +
                '<div class="mdif-categorys"></div>' +
                '<div class="mdif-container"></div>' +
                '<div class="mdif-page"></div>' +
                '</div>';
            $(target).after(box);
            $('#' + idName).click(function(event) {
                event.stopPropagation();
                event.preventDefault();
            });

            $('#' + idName + ' #mdif-close-font').click(function() {
                $('#' + idName).toggle();
            });
            showCategorys();
            showFonts(defaultCategory);

        });
        // 插入图标样式名字到输入框
        $.fn.insertFontText = function(text) {
            this.each(function() {
                if (this.tagName !== 'INPUT' && this.tagName !== 'TEXTAREA') {
                    return;
                }
                this.value = text;
            });
            return this;
        };
        // 显示全部分类：category
        function showCategorys() {
            $('#' + idName + ' .mdif-categorys').html('');
            for (var i = 0; i < categorys.length; ++i) {
                $('#' + idName + ' .mdif-categorys').append($('<a href="javascript:void(0);">' + categorys[i] + '</a>'));
            }
            $('#' + idName + ' .mdif-categorys a').click(function() {
                showFonts($(this).text());
            });
            $('#' + idName + ' .mdif-categorys a').each(function() {
                if ($(this).text() == defaultCategory) {
                    $(this).addClass('current');
                    return;
                }
            });
        }
        // 分页显示字体图标：fonts
        function showFonts(category, page) {
            category = category ? category : defaultCategory;
            page = page ? page - 1 : 0;
            $('#' + idName + ' .mdif-container').html('');
            $('#' + idName + ' .mdif-page').html('');
            // 该分类总记录数
            var total = fonts[category].length;
            // 该分类总页数
            var pages = total % size == 0 ? total / size : total / size + 1;
            for (var i = page * size; i < (page + 1) * size && i < total; ++i) {
                var faceImg = '<a href="javascript:void(0);" title="zmdi zmdi-' +
                    fonts[category][i] +
                    '"><i class="zmdi zmdi-' +
                    fonts[category][i] +
                    '"></i></a>';
                $('#' + idName + ' .mdif-container').append(faceImg);
            }
            $('#' + idName + ' .mdif-container a').click(function() {
                target.insertFontText($(this).attr('title'));
                $('#' + idName).toggle();
            });
            for (var i = 1; i < pages; ++i) {
                var p = '<a href="javascript:void(0);"' + (i == page + 1 ? ' class="current"' : '') + '>' + i + '</a>';
                $('#' + idName + ' .mdif-page').append(p);
            }
            $('#' + idName + ' .mdif-page a').click(function() {
                showFonts(category, $(this).text());
            });
            $('#' + idName + ' .mdif-categorys a.current').removeClass('current');
            $('#' + idName + ' .mdif-categorys a').each(function() {
                if ($(this).text() == category) {
                    $(this).addClass('current');
                    return;
                }
            });
        }
    }
});
