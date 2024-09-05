/*
 * jQuery UI Multicolumn Autocomplete Widget Plugin 2.2
 *
 * Depends:
 *   - jQuery UI Autocomplete widget
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
*/
$.widget('custom.mcautocomplete', $.ui.autocomplete, {
    _create: function() {
      this._super();
      this.widget().menu( "option", "items", "> tr:not(.ui-widget-header)" );
    },
    _renderMenu: function(ul, items) {
        var self = this, thead;
        let createOption='';
        if (this.options.showHeader) {
            table=$('<div class="ui-widget-header" style="width:100%;"></div>');
            // Column headers
            $.each(this.options.columns, function(index, item) {
                table.append('<span style="float:left;min-width:' + item.minWidth + ';">' + item.name + '</span>');
                if(item.name == 'Account Head'){
                    createOption = 'Create GL';
                }else if(item.name == 'Supplier Code/Name'){
                    createOption='Create Supplier';
                }
            });
			table.append('<div style="clear: both;"></div>');
            ul.append(table);
            if(createOption!=''){
            ul.append('<li class="ui-menu-item '+createOption+'"> <a href="#" class="create-gl-link"><span style="float:left;min-width:400px;">'+createOption+'</span><span style="float:left;min-width:400px;"></span><span style="float:left;min-width:400px;"></span><div style="clear: both;"></div></a></li>');
            }
        }
        // List items
        $.each(items, function(index, item) {
            self._renderItem(ul, item);
        });
    },
    _renderItem: function(ul, item) {
		var t = '',
			result = '';
		
		$.each(this.options.columns, function(index, column) {
			var chk = item[column.valueField ? column.valueField : index];
			
			if(chk=='')
				chk = '-';
			t += '<span style="float:left;min-width:' + column.minWidth + ';">' + chk + '</span>'
	});
		ul.append('<div class="ulChlk">');  
		result = $('<li></li>')
			.data('ui-autocomplete-item', item)
			.append('<a class="mcacAnchor">' + t + '<div style="clear: both;"></div></a>')
			.appendTo(ul);
		return result;
    }
});
