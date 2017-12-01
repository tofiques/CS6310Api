/**
 * Created by tofiques on 3/20/17.
 */


$(document).ready(function () {

    $("#courses").change(function () {
        if (this.checked) {
            $("#hasKey").removeClass("hidden")
        }
        else {
            $("#hasKey").addClass("hidden")
        }
    });


});





