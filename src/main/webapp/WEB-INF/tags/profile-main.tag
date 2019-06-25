<%@ tag pageEncoding="UTF-8"  trimDirectiveWhitespaces="true" %>

<div class="panel panel-primary">
    <a href="/edit"><img src="external/test-data/photos/man1.jpg" class="img-responsive img-rounded"/></a>
    <h1 class="text-center">
        <a style="color: black;" href="/edit">${fullName != null ? fullName : 'NULL'}</a>
    </h1>
    <h6 class="text-center">
        <strong>Moscow, Russia</strong>
    </h6>
    <h6 class="text-center">
            <strong>Age: </strong> 33, <strong>Birthday: </strong> Sep 22, 2000
        </h6>
    <div class="list-group contacts">
        <a class="list-group-item" href="tel: +789092384234">+789092384234</a>

    </div>
</div>