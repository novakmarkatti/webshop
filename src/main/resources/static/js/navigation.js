$(document).ready(function() {
    // Menu
    var pathname = window.location.pathname;

    $(".nav-item").removeClass("active");
    
    $(".nav-item a").each(function() {
        if ($(this).attr("href") === pathname) {
            $(this).parent().addClass("active");
        }
    });
});