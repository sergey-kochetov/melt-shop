<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="profile" required="false" type="ru.com.melt.info.entity.Profile"%>
<%@ attribute name="showEdit"  required="false" type="java.lang.Boolean" %>

<div class="panel panel-primary">
	<a href="/edit"><img class="img-responsive photo" src="${profile.largePhoto}" alt="photo"></a>
	<h1 class="text-center">
		<a style="color: black;" href="/edit">${profile.fullName}</a>
	</h1>
	<h6 class="text-center">
		<strong>${profile.country}, ${profile.city}</strong>
	</h6>
	<h6 class="text-center">
		<strong>Age:</strong> ${profile.age }, <strong>Birthday: </strong>
		<fmt:formatDate value="${profile.birthDay }" type="date" dateStyle="medium"/>
	</h6>
	<div class="list-group contacts">
    		<a href="tel:${profile.phone }" class="list-group-item"><i class="fa fa-phone"></i> ${profile.phone }</a>
    		<a href="mailto:${profile.email}" class="list-group-item"><i class="fa fa-envelope"></i> ${profile.email}</a>
    		<c:if test="${profile.contacts.skype != null }">
    		<a href="javascript:void(0);" class="list-group-item"><i class="fa fa-skype"></i>${profile.contacts.skype}</a>
    		</c:if>
    		<c:if test="${profile.contacts.vkontakte != null }">
    		<a href="${profile.contacts.vkontakte}" class="list-group-item" target="_blank"><i class="fa fa-vk"></i> ${profile.contacts.vkontakte}</a>
    		</c:if>
    		<c:if test="${profile.contacts.facebook != null }">
    		<a href="${profile.contacts.facebook}" class="list-group-item" target="_blank"><i class="fa fa-facebook"></i> ${profile.contacts.facebook}</a>
    		</c:if>
    		<c:if test="${profile.contacts.linkedin != null }">
    		<a href="${profile.contacts.linkedin}" class="list-group-item" target="_blank"><i class="fa fa-linkedin"></i> ${profile.contacts.linkedin}</a>
    		</c:if>
    		<c:if test="${profile.contacts.github != null }">
    		<a href="${profile.contacts.github}" class="list-group-item" target="_blank"><i class="fa fa-github"></i> ${profile.contacts.github}</a>
    		</c:if>
    		<c:if test="${profile.contacts.stackoverflow != null }">
    		<a href="${profile.contacts.stackoverflow}" class="list-group-item" target="_blank"><i class="fa fa-stack-overflow"></i> ${profile.contacts.stackoverflow}</a>
    		</c:if>
    	</div>
</div>