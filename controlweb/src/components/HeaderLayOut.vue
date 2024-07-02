<template>
<a-layout-header style="background-color: #1677ff;">

<a-flex justify="space-between" >
    <div class="logo" > Control  </div>

    <div style="width: 300px;">  
        <a-menu style="background-color: #1677ff;"
            theme="dark"
            mode="horizontal">
            <a-menu-item key="1"  > {{ $t('home.name') }}</a-menu-item>
            <a-menu-item key="2">  <RouterLink  :to="{name:'sys'}" >{{ $t('button.syssetting') }}</RouterLink>   </a-menu-item>

            <a-menu-item key="3">{{ $t('button.usersetting') }} </a-menu-item>
    <!-- <a-menu-item key="4"> <I18N></I18N> </a-menu-item> -->
        </a-menu>  
    </div>
   

</a-flex>
</a-layout-header>



</template>

<script setup>

</script>

<style>

</style>