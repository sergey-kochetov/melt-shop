<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-sm-6">
			<resume:profile-main profile="${profile}" showEdit="${showEdit}"/>
			<div class="hidden-xs">
				<resume:profile-languages languages="${profile.languages}" showEdit="${showEdit}"/>
				<resume:profile-hobbies hobbies="${profile.hobbies}" showEdit="${showEdit}"/>
				<resume:profile-info profile="${profile}" showEdit="${showEdit}"/>
			</div>
		</div>
		<div class="col-md-8 col-sm-6">
			<resume:profile-objective profile="${profile}" showEdit="${showEdit}"/>
			<resume:profile-skills skills="${profile.skills}" showEdit="${showEdit}"/>
			<resume:profile-practics practics="${profile.practics}" showEdit="${showEdit}"/>
			<resume:profile-certificates certificates="${profile.certificates}" showEdit="${showEdit}"/>
			<resume:profile-cources courses="${profile.courses}" showEdit="${showEdit}"/>
			<resume:profile-education educations="${profile.educations}" showEdit="${showEdit}"/>
		</div>
		<div class="col-xs-12 visible-xs-block">
			<resume:profile-languages languages="${profile.languages}" showEdit="${showEdit}"/>
			<resume:profile-hobbies hobbies="${profile.hobbies}" showEdit="${showEdit}"/>
			<resume:profile-info profile="${profile}" showEdit="${showEdit}"/>
		</div>
	</div>
</div>