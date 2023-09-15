$(document).ready(function(){
	
	$("#request-link").click(function(e){
		e.preventDefault();
		$.post(ctx + "/resource/request", $("form").serialize(), function(data) {
			alert(data);
		});
		return false;
	});
	
	$("#pricing-link-deferredresult").click(function(e){
		e.preventDefault();
		// Send Async request and get response with Deferred Result
		$.get(ctx + "/resource/pricing/deferredresult", function(data) {
			$("#cost").val(data);
		});
		return false;
	});

	$("#pricing-link-callableobject").click(function(e){
		e.preventDefault();
		// Send Async request and get response with Callable Object
		$.get(ctx + "/resource/pricing/callableobject", function(data) {
			$("#cost").val(data);
		});
		return false;
	});

	$("#pricing-link-stream-sse").click(function(e){
		e.preventDefault();
		var rbe = new EventSource("/async-requests/resource/pricing/sse");
		rbe.onmessage = function(event) {
			$("#cost").val(event.data);
		};
		return false;
	});
});