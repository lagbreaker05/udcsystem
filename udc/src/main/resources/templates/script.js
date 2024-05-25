
    <script type="text/javascript">
        $(document).ready(function() {
            $("#locales").change(function () {
                var selectedOption = $(this).val();
                if (selectedOption != ''){
                    window.location.replace(selectedOption);
                }
            })};
        );
    </script>