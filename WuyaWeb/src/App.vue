<template>
	<div id="app">
		<router-view/>
	</div>
</template>

<script>
	export default {
		name: 'app'
	}
</script>

<style>
#app{
  overflow-y: scroll;
}
#app::-webkit-scrollbar {
  display: none;
}

</style>
