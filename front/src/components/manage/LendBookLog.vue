<template>
  <div>
    <head-top></head-top>
    <el-card>
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input v-model="searchInfo"
                    clearable
                    placeholder="查询图书(根据名称、作者、出版社)">
            <el-button slot="append"
                       icon="el-icon-search"
                       @click="getLendLogByInfo"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button @click="clearFilter">清除状态筛选</el-button>
        </el-col>
      </el-row>
      <el-table :data="lendLogList"
                ref="filterTable"
                border
                height="740"
                stripe
                style="width: 100%">
        <el-table-column prop="bookName"
                         label="书籍名称">
        </el-table-column>
        <el-table-column prop="bookAuthor"
                         label="书籍作者">
        </el-table-column>
        <el-table-column prop="bookPublish"
                         label="书籍出版社">
        </el-table-column>
        <el-table-column prop="bookISBN"
                         label="书籍ISBN">
        </el-table-column>
        <el-table-column prop="userFullName"
                         label="用户昵称">
        </el-table-column>
        <el-table-column prop="lendDate"
                         label="借书日期"
                         sortable>
          <template slot-scope="scope">{{scope.row.lendDate | formatDate}}</template>
        </el-table-column>
        <el-table-column prop="backDate"
                         label="还书日期"
                         sortable>
          <template slot-scope="scope">{{scope.row.backDate | formatDate}}</template>
        </el-table-column>
        <el-table-column prop="state"
                         label="状态"
                         :filters="[{ text: '已还', value: '已还' }, { text: '未还', value: '未还' }]"
                         :filter-method="filterTag"
                         filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag :type="scope.row.state=='未还'
                    ? 'primary'
                    : 'success'">
              {{scope.row.state}}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>
<script>
import HeadTop from './ManageHeadTop'
import { formatDate } from '@/assets/js/date'
export default {
  filters: {
    formatDate (time) {
      if (time == null) return null
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  components: {
    HeadTop
  },
  created () {
    this.getStoreLog()
  },
  data () {
    return {
      lendLogList: [],
      searchInfo: ''
    }
  },
  methods: {
    async getStoreLog () {
      const { data: res } = await this.$http.get('allLendLog?info=')
      if (res.message == 'success') {
        this.lendLogList = res.data.lendLogList
      } else {
        this.$message.error('服务器出错')
      }
    },
    async getLendLogByInfo () {
      const { data: res } = await this.$http.get('allLendLog?info=' +
        this.searchInfo)
      if (res.message == 'success') {
        this.lendLogList = res.data.lendLogList
      } else {
        this.$message.error('服务器出错')
      }
    },
    filterTag (value, row) {
      return row.state === value
    },
    clearFilter () {
      this.$refs.filterTable.clearFilter()
    }
  }
}
</script>
<style lang="less">
</style>
