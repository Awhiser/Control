<template  >
 


    <a-table  :columns="columns" :data-source="data">

<template #headerCell="{ column }">
  <template v-if="column.key === 'name'">
    <span >
      <smile-outlined />
      Name
    </span>
  </template>
</template>

<template #bodyCell="{ column, record }">
  <template v-if="column.key === 'name'">
    <a>
      {{ record.name }}
    </a>
  </template>
  <template v-else-if="column.key === 'tags'">
    <span>
      <a-tag
        v-for="tag in record.tags"
        :key="tag"
        :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
      >
        {{ tag.toUpperCase() }}
      </a-tag>
    </span>
  </template>
  <template v-else-if="column.key === 'action'">
    <span>
      <a>Invite 一 {{ record.name }}</a>
      <a-divider type="vertical" />
      <a>Delete</a>
      <a-divider type="vertical" />
      <a class="ant-dropdown-link">
        More actions
        <down-outlined />
      </a>
    </span>
  </template>
</template>
</a-table>



  
  
</template>
<script lang="ts" setup>
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
const columns = [
  {
    title: 'title',
    dataIndex: 'title',
    key: 'title',
  },
  {
    title: 'id',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'type',
    dataIndex: 'type',
    key: 'type',
  },
  {
    title: 'priority',
    key: 'priority',
    dataIndex: 'priority',
  },
  {
    title: 'tags',
    key: 'tags',
    dataIndex: 'tags',
  },
  {
    title: 'status',
    key: 'status',
    dataIndex: 'status',
  },
];

const data = [
  {
    id: 'X-1',
    title: 'John Brown',
    type: 'Task',
    priority: 'Easy',
    tags: ['nice', 'developer'],
    status: '处理中',
  },
  {
    id: 'X-2',
    title: 'John Brown',
    type: 'Task',
    priority: 'Easy',
    tags: ['nice', 'developer'],
    status: '处理中',
  },
  {
    id: 'X-3',
    title: 'John Brown',
    type: 'Task',
    priority: 'Easy',
    tags: ['nice', 'developer'],
    status: '处理中',
  },
];
</script>