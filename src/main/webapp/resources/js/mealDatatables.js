var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: function (data) {
            updateTableByData(data);
        }
    });
    return false;
}

$(function () {
    datatableApi = $('#datatable').DataTable(
        {
            "sAjaxSource": ajaxUrl,
            "sAjaxDataProp": "",
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (datetime, type, row) {
                        if (type == 'display') {
                            var dateObject = new Date(datetime);
                            return '<span>' + dateObject.toISOString() + '</span>';
                        }
                        return datetime;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.exceed) {
                    $(row).css("color", "green");
                } else {
                    $(row).css("color", "red");
                }
            },
            "initComplete": function() {
                $('#filter').submit(function () {
                    updateTable();
                    return false;
                });
                makeEditable();
            }
        });


});