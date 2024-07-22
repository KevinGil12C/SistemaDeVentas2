package vistaVentas;

import bo.*;
import entity.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;
import reportes.GeneraReporte;
import rojerusan.RSNotifyFade;
import vistaPrincipal.Principal;

/**
 *
 * @author Kevscl
 */
public class principalVentas extends javax.swing.JPanel {

    int cont = 0;
    int idVenta = 0;
    String producto = "";
    float pUnitario = 0;
    int cantidad = 0;
    float total = 0;
    float subtotal = 0;
    float iva = 0;
    float pTotal = 0;
    DefaultTableModel modelo;
    /**
     * Creates new form principalVentas
     */
    PagoVentas pv = new PagoVentas();
    PagoVentaBO pvbo = new PagoVentaBO();
    ProductoBO pbo = new ProductoBO();
    Producto p = new Producto();
    Venta v = new Venta();
    VentaBO vbo = new VentaBO();
    DetalleVenta dv = new DetalleVenta();
    Cliente c = new Cliente();
    ClienteBO cbo = new ClienteBO();
    private JPopupMenu popupMenu = new JPopupMenu();
    private Principal prin;

    public principalVentas() {
        initComponents();
        mostrarCombo();
        mostrarClientes();
        reset();
        idMax();
        jPopupMenu1.add(menu);
        txtVendedor.setVisible(false);
        comboPago.setSelectedIndex(0);
        txtCliente.setText("VENTAS PÚBLICO EN GENERAL");
        addEventKey();
    }

    private void addEventKey() {
        // Acción para la tecla F1
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
        Action f1Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocas el código que deseas ejecutar cuando se presione la tecla F1
                // Por ejemplo, llamar a un método para mostrar ayuda o instrucciones
                agregar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f1, "F1");
        this.getActionMap().put("F1", f1Action);

        // Acción para la tecla F2
        KeyStroke f2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false);
        Action f2Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocas el código que deseas ejecutar cuando se presione la tecla F2
                cobrar();

            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(f2, "F2");
        this.getActionMap().put("F2", f2Action);

        // Acción para la tecla ESC
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Aquí colocas el código que deseas ejecutar cuando se presione la tecla ESC
                limpiar();
            }
        };
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(esc, "ESC");
        this.getActionMap().put("ESC", escAction);

    }

    public void idMax() {
        txtIdVenta.setText(vbo.getMaxID());
    }

    public void reset() {
        cont = 1;
        idVenta = Integer.parseInt(txtIdVenta.getText());
        producto = "";
        pUnitario = 0;
        cantidad = 0;
        total = 0;
        subtotal = 0;
        iva = 0;
        pTotal = 0;
    }

    public void mostrarCombo() {
        List<Producto> matchingProductos = pbo.getMatchingProductos();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccione un producto");
        for (Producto producto : matchingProductos) {
            model.addElement(producto.getNomProducto() + " " + producto.getTam());
        }
        comboProductos.setModel(model);

        AutoCompleteDecorator.decorate(comboProductos);

    }

    public void mostrarClientes() {
        List<Cliente> clientes = cbo.obtenerClientes();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Cliente cl : clientes) {
            model.addElement(cl.getNomCliente() + " " + cl.getApe1Cliente() + " " + cl.getApe2Cliente());
        }
        // ...
        JList<String> list = new JList<>(model);
        AutoCompleteDecorator.decorate(list, txtCliente, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);

        // Seleccionar el primer cliente
        if (list.getModel().getSize() > 0) {
            list.setSelectedIndex(0);
            list.ensureIndexIsVisible(0);
        }

    }

    public void agregar() {
        if (!txtCantidad.getText().isEmpty()) {
            DecimalFormat df = new DecimalFormat("#.00");

            idVenta = Integer.parseInt(txtIdVenta.getText());
            producto = comboProductos.getSelectedItem() + "";
            pUnitario = Float.parseFloat(txtPrecio.getText());
            cantidad = Integer.parseInt(txtCantidad.getText());
            total = pUnitario * cantidad;

            //Definimos los datos de la tabla
            modelo = (DefaultTableModel) tbVentas.getModel();

            // Verificar si el producto ya existe en la tabla
            int rowCount = modelo.getRowCount();
            boolean productoExistente = false;
            int filaExistente = -1;
            for (int i = 0; i < rowCount; i++) {
                String productoTabla = modelo.getValueAt(i, 2).toString();
                if (productoTabla.equals(producto)) {
                    productoExistente = true;
                    filaExistente = i;
                    break;
                }
            }

            if (productoExistente) {
                // Actualizar la cantidad y el precio total del producto existente
                int cantidadExistente = Integer.parseInt(modelo.getValueAt(filaExistente, 3).toString());
                float precioTotalExistente = Float.parseFloat(modelo.getValueAt(filaExistente, 5).toString());

                int nuevaCantidad = cantidadExistente + cantidad;
                float nuevoPrecioTotal = precioTotalExistente + total;

                modelo.setValueAt(nuevaCantidad, filaExistente, 3);
                modelo.setValueAt(df.format(nuevoPrecioTotal), filaExistente, 5);
                recalcularTotales();
            } else {
                // Agregar un nuevo elemento a la tabla
                Object[] obj = new Object[6];
                obj[0] = cont;
                obj[1] = idVenta;
                obj[2] = producto;
                obj[3] = cantidad;
                obj[4] = df.format(pUnitario);
                obj[5] = df.format(total);

                modelo.addRow(obj);
                cont++;
                recalcularTotales();
            }

            tbVentas.setModel(modelo);
            comboProductos.setSelectedIndex(0);
            txtStock.setText("");
            txtPrecio.setText("");
            txtCantidad.setText("");
            txtClientePago.setVisible(false);
        } else {
            new rojerusan.RSNotifyFade("¡WARNING!", "¡Ingrese la cantidad!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);

        }
    }

    private void recalcularTotales() {
        DecimalFormat df = new DecimalFormat("#.00");

        DefaultTableModel modelo = (DefaultTableModel) tbVentas.getModel();
        int rowCount = modelo.getRowCount();

        float subtotal = 0;
        float iva = 0;
        float total = 0;

        for (int i = 0; i < rowCount; i++) {
            float precioTotal = Float.parseFloat(modelo.getValueAt(i, 5).toString());
            subtotal += precioTotal / 1.16;
            iva += precioTotal * 0.16;
            total += precioTotal;
        }

        if (comboPago.getSelectedItem().equals("Pago a plazos.")) {
            subtotal *= 1.3;
            iva *= 1.3;
            total *= 1.3; // Aumento del 30% en el total si es pago a plazos
        }

        txtSubtotal.setText(df.format(subtotal));
        txtIva.setText(df.format(iva));
        txtTotal.setText(df.format(total));

    }

    public void guardaVenta() {
        v = new Venta();
        Date fechaSeleccionada = dateFecha.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormateada = formato.format(fechaSeleccionada);

        int idVenta = Integer.parseInt(txtIdVenta.getText());
        String empleado = txtVendedor.getText();
        String cliente = txtCliente.getText();

        String mpago = comboPago.getSelectedItem() + "";
        float total = Float.parseFloat(txtTotal.getText());

        if (idVenta != 0 && fechaSeleccionada != null && empleado != null && !empleado.isEmpty()
                && cliente != null && !cliente.isEmpty() && mpago != null && !mpago.isEmpty()
                && total != 0) {
            // Los datos son válidos, continúa con el proceso
            v.setIdVenta(idVenta);
            v.setFecha(fechaFormateada);
            v.setmPago(mpago);

            // Convertir el valor total a un String y luego formatear a dos decimales
            String totalString = Float.toString(total);
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String totalFormatted = decimalFormat.format(Float.parseFloat(totalString));
            v.setTotal(Float.parseFloat(totalFormatted));

            String mensaje = vbo.agregarVenta(v, cliente, empleado);
        } else {
            new rojerusan.RSNotifyFade("¡WARNING!", "¡Llene todos los campos!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
        }
    }

    public void guardaDetalleVenta() {
        dv = new DetalleVenta();
        vbo = new VentaBO();
        for (int i = 0; i < tbVentas.getRowCount(); i++) {
            dv.setIdDetalleVenta(Integer.parseInt(tbVentas.getValueAt(i, 0) + ""));
            dv.setIdVenta(Integer.parseInt(txtIdVenta.getText()));
            String pro = tbVentas.getValueAt(i, 2) + "";
            dv.setCantidad(Integer.parseInt(tbVentas.getValueAt(i, 3) + ""));
            dv.setpUnitario(Float.parseFloat(tbVentas.getValueAt(i, 4) + ""));
            dv.setTotal(Float.parseFloat(tbVentas.getValueAt(i, 5) + ""));
            String mensaje = vbo.agregarDetalleVenta(dv, pro);
            if (!mensaje.equals("GUARDADO CORRECTAMENTE")) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Error al guardar la venta!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
            } else {
                new rojerusan.RSNotifyFade("¡SUCCESS!", "¡Guardado Correctamente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.SUCCESS).setVisible(true);
            }
        }
        //generaPDF();
    }

    public void limpiar() {
        if (modelo.getRowCount() == -1) {
            reset();
            recalcularTotales();
            idMax();
            txtSubtotal.setText("");
            txtTotal.setText("");
            txtIva.setText("");
            txtClientePago.setText("");
            labelCam.setVisible(true);
            labelCambio.setText("");
            labelImporte.setText("");
            // Validar el comboPago antes de obtener el índice seleccionado
            if (comboPago.getItemCount() > 0) {
                comboPago.setSelectedIndex(0);
            } else {
                // Si el comboPago está vacío, puedes mostrar un mensaje o realizar alguna acción en consecuencia
                // Por ejemplo, deshabilitar botones relacionados con el combo o mostrar un mensaje de error.
            }
        } else {
            // La tabla tiene filas, entonces puedes continuar con tus operaciones.
            reset();
            recalcularTotales();
            idMax();
            txtSubtotal.setText("");
            txtTotal.setText("");
            txtIva.setText("");
            modelo.setRowCount(0);
            txtClientePago.setText("");
            labelCam.setVisible(true);
            labelCambio.setText("");
            labelImporte.setText("");
            // Validar el comboPago antes de obtener el índice seleccionado
            if (comboPago.getItemCount() > 0) {
                comboPago.setSelectedIndex(0);
            } else {
                // Si el comboPago está vacío, puedes mostrar un mensaje o realizar alguna acción en consecuencia
                // Por ejemplo, deshabilitar botones relacionados con el combo o mostrar un mensaje de error.
            }
        }

    }

    public void pago() {
        int idpago = Integer.parseInt(pvbo.getMaxID());
        int idVenta = Integer.parseInt(txtIdVenta.getText());
        Date fechaSeleccionada = dateFecha.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormateada = formato.format(fechaSeleccionada);

// Validar que el campo labelImporte no esté vacío y sea un número válido
        float monto = 0.0f;
        try {
            monto = Float.parseFloat(labelImporte.getText());
        } catch (NumberFormatException ex) {
            // Manejar la excepción, por ejemplo, mostrar un mensaje de error
            new rojerusan.RSNotifyFade("¡ERROR!", "El monto no es válido.", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.ERROR).setVisible(true);
            return; // Salir del método o función si el monto no es válido
        }

        String cliente = txtCliente.getText().equals("VENTAS PÚBLICO EN GENERAL") ? txtClientePago.getText() : txtCliente.getText();
        pv = new PagoVentas();
        pv.setIdPago(idpago);
        pv.setIdVenta(idVenta);
        pv.setFechaPago(fechaFormateada);

// Formatear el valor monto a dos decimales
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String montoFormatted = decimalFormat.format(monto);
        pv.setAbono(Float.parseFloat(montoFormatted));

        pv.setNombreCliente(cliente);
        String mensaje = pvbo.agregarPagoVenta(pv);
        System.out.println(mensaje);
    }

    public void cobrar() {
        if (comboPago.getSelectedItem().equals("Pago a plazos.")) {
            guardaVenta();
            guardaDetalleVenta();
            pago();
            GeneraReporte g = new GeneraReporte();
            g.generaPDF(Integer.parseInt(txtIdVenta.getText()));
            limpiar();
        } else {
            guardaVenta();
            guardaDetalleVenta();
            GeneraReporte g = new GeneraReporte();
            g.generaPDF(Integer.parseInt(txtIdVenta.getText()));
            limpiar();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        btnQuitar = new RSMaterialComponent.RSButtonMaterialIconTwo();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rSLabelTextIcon1 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        jPanel4 = new javax.swing.JPanel();
        txtTotal = new RSMaterialComponent.RSTextFieldIconTwo();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIva = new RSMaterialComponent.RSTextFieldIconTwo();
        jLabel3 = new javax.swing.JLabel();
        txtSubtotal = new RSMaterialComponent.RSTextFieldIconTwo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVentas = new RSMaterialComponent.RSTableMetro();
        txtCliente = new RSMaterialComponent.RSTextFieldIconTwo();
        jPanel3 = new javax.swing.JPanel();
        rSLabelTextIcon3 = new RSMaterialComponent.RSLabelTextIcon();
        dateFecha = new newscomponents.RSDateChooser();
        comboPago = new RSMaterialComponent.RSComboBox();
        btnCobrar = new RSMaterialComponent.RSButtonMaterialIconTwo();
        btnCancelar = new RSMaterialComponent.RSButtonMaterialIconTwo();
        txtIdVenta = new RSMaterialComponent.RSTextFieldTwo();
        labelImporte = new RSMaterialComponent.RSTextFieldIconTwo();
        labelCambio = new RSMaterialComponent.RSTextFieldIconTwo();
        jLabel4 = new javax.swing.JLabel();
        labelCam = new javax.swing.JLabel();
        comboProductos = new RSMaterialComponent.RSComboBox();
        txtStock = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtPrecio = new RSMaterialComponent.RSTextFieldMaterialIcon();
        txtCantidad = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnAgregar = new RSMaterialComponent.RSButtonMaterialIconTwo();
        txtVendedor = new RSMaterialComponent.RSTextFieldIconTwo();
        txtClientePago = new RSMaterialComponent.RSTextFieldIconTwo();

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnQuitar.setText("QUITAR PRODUCTO");
        btnQuitar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        menu.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPopupMenu1.getAccessibleContext().setAccessibleParent(menu);

        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(38, 86, 186));

        rSLabelTextIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelTextIcon1.setText("VENTA DE PRODUCTOS");
        rSLabelTextIcon1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSLabelTextIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.STORE);

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtTotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTotal.setText("0.00");
        txtTotal.setBorderColor(new java.awt.Color(255, 255, 255));
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotal.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        txtTotal.setPlaceholder("TOTAL");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TOTAL:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("IVA 16%:");

        txtIva.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtIva.setText("0.00");
        txtIva.setBorderColor(new java.awt.Color(255, 255, 255));
        txtIva.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIva.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        txtIva.setPlaceholder("TOTAL");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("SUB TOTAL");

        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtSubtotal.setText("0.00");
        txtSubtotal.setBorderColor(new java.awt.Color(255, 255, 255));
        txtSubtotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSubtotal.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        txtSubtotal.setPlaceholder("TOTAL");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))))
        );

        tbVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CONT", "ID VENTA", "PRODUCTO", "CANTIDAD", "P/U", "TOTAL"
            }
        ));
        tbVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbVentasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbVentas);

        txtCliente.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BOX);
        txtCliente.setPlaceholder("CLIENTE");
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rSLabelTextIcon3.setBackground(new java.awt.Color(51, 0, 255));
        rSLabelTextIcon3.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelTextIcon3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSLabelTextIcon3.setText("NO. VENTA:");
        rSLabelTextIcon3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSLabelTextIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK);

        comboPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Método de pago", "Tarjeta de crédito o débito.", "Transferencia electrónica.", "Pago a plazos.", "Efectivo." }));
        comboPago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPagoActionPerformed(evt);
            }
        });

        btnCobrar.setText("COBRAR VENTA (F2)");
        btnCobrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCobrar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar.setText("CANCELAR VENTA (ESC)");
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtIdVenta.setForeground(new java.awt.Color(0, 0, 0));
        txtIdVenta.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtIdVenta.setText("1");
        txtIdVenta.setBorderColor(new java.awt.Color(255, 255, 255));

        labelImporte.setBorderColor(new java.awt.Color(255, 255, 255));
        labelImporte.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        labelImporte.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        labelImporte.setPlaceholder("IMPORTE");
        labelImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                labelImporteKeyReleased(evt);
            }
        });

        labelCambio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        labelCambio.setBorderColor(new java.awt.Color(255, 255, 255));
        labelCambio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        labelCambio.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        labelCambio.setPlaceholder("CAMBIO");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("IMPORTE:");

        labelCam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelCam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCam.setText("CAMBIO:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rSLabelTextIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(comboPago, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(labelCam))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(labelCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSLabelTextIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        comboProductos.setMaximumRowCount(50);
        comboProductos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un producto" }));
        comboProductos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductosActionPerformed(evt);
            }
        });

        txtStock.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VERIFIED_USER);
        txtStock.setPlaceholder("Stock");

        txtPrecio.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        txtPrecio.setPlaceholder("P/Unitario");

        txtCantidad.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK_CIRCLE);
        txtCantidad.setInheritsPopupMenu(true);
        txtCantidad.setPlaceholder("Cantidad");
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        btnAgregar.setText("AGREGAR (F1)");
        btnAgregar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtVendedor.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BOX);
        txtVendedor.setPlaceholder("CLIENTE");
        txtVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVendedorKeyReleased(evt);
            }
        });

        txtClientePago.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BOX);
        txtClientePago.setPlaceholder("CLIENTE");
        txtClientePago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientePagoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtClientePago, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClientePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        cobrar();
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (txtCliente.getText().equals("VENTAS PÚBLICO EN GENERAL") && comboPago.getSelectedItem().equals("Pago a plazos.")) {
            if (!txtClientePago.getText().isEmpty()) {
                btnCobrar.setVisible(true);
            }
        } else {
            txtClientePago.setVisible(false);
            btnCobrar.setVisible(true);
            labelCambio.setVisible(true);
            labelCam.setVisible(true);
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed

        String selectedProduct = comboProductos.getSelectedItem() + "";
        if (selectedProduct.equals("Seleccione un producto")) {
            // Limpiar los campos de texto para el stock y precio
            txtStock.setText("");
            txtPrecio.setText("");
            txtClientePago.setVisible(true);
        } else {
            // Obtener el producto seleccionado
            List<Producto> matchingProductos = pbo.getMatchingProductos();
            Producto producto = matchingProductos.get(comboProductos.getSelectedIndex() - 1);

            // Obtener el stock y precio del producto seleccionado
            int stock = producto.getStock();
            float precio = producto.getPrecio();

            // Llenar los campos de texto con el stock y precio
            txtStock.setText(String.valueOf(stock));
            txtPrecio.setText(String.valueOf(precio));
            txtClientePago.setVisible(false);
        }
    }//GEN-LAST:event_comboProductosActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int selectedRow = tbVentas.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel modelo = (DefaultTableModel) tbVentas.getModel();

            // Eliminar la fila seleccionada del modelo de la tabla
            modelo.removeRow(selectedRow);

            // Actualizar el contador
            cont--;

            // Actualizar el índice de las filas restantes en la columna de contador
            int rowCount = modelo.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                modelo.setValueAt(i + 1, i, 0);
            }

            // Actualizar la tabla con el nuevo modelo y los cambios realizados
            tbVentas.setModel(modelo);
            jPopupMenu1.setVisible(false);
            recalcularTotales();
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void tbVentasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVentasMouseReleased
        if (evt.isPopupTrigger()) {
            // Obtener la fila seleccionada
            int fila = tbVentas.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila != -1) {
                // Mostrar el JPopupMenu en la posición del clic
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            } else {
                //listar();
                jPopupMenu1.setVisible(false);
            }
        }
    }//GEN-LAST:event_tbVentasMouseReleased

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        if (!txtCantidad.getText().isEmpty()) {
            int cant = Integer.parseInt(txtCantidad.getText());
            int stock = Integer.parseInt(txtStock.getText());
            if (cant > stock) {
                new rojerusan.RSNotifyFade("¡ERROR!", "¡Stock insuficiente!", 3, RSNotifyFade.PositionNotify.BottomRight, RSNotifyFade.TypeNotify.WARNING).setVisible(true);
                txtCantidad.setText("");
            } else {
                //No muestra mensaje
            }
        }

    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtVendedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendedorKeyReleased

    }//GEN-LAST:event_txtVendedorKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void labelImporteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labelImporteKeyReleased
        try {
            float importe = Float.parseFloat(labelImporte.getText());
            float total = Float.parseFloat(txtTotal.getText());
            float cambio = importe - total;
            DecimalFormat formato = new DecimalFormat("#.00");
            String numero_formateado = formato.format(cambio);
            labelCambio.setText(numero_formateado);
        } catch (NumberFormatException e) {
            // Manejo de excepciones en caso de que las cadenas no sean números válidos
            labelCambio.setText("");
        }

    }//GEN-LAST:event_labelImporteKeyReleased

    private void comboPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPagoActionPerformed
        String selectedPayment = comboPago.getSelectedItem().toString().trim();
        String clienteText = txtCliente.getText().trim(); // Asegúrate de eliminar espacios en blanco alrededor del texto.

// Usar constantes en lugar de cadenas literales
        final String PAGO_A_PLAZOS = "Pago a plazos.";
        final String VENTAS_PUBLICO_GENERAL = "VENTAS PÚBLICO EN GENERAL";

        if (selectedPayment.equals(PAGO_A_PLAZOS) && clienteText.equals(VENTAS_PUBLICO_GENERAL)) {
            txtClientePago.setEnabled(true);
            txtClientePago.setVisible(true);
            labelCambio.setVisible(false);
            labelCam.setVisible(false);

            // Validar si el campo txtClientePago no está vacío
            if (!txtClientePago.getText().isEmpty()) {
                btnCobrar.setVisible(true);
            } else {
                btnCobrar.setVisible(false);
            }
        } else {
            txtClientePago.setEnabled(false);
            txtClientePago.setVisible(false); // Asegurarse de ocultar el txtClientePago
            labelCam.setVisible(true);
            labelCambio.setVisible(true);
            btnCobrar.setVisible(true); // Ocultar btnCobrar cuando no se cumplan las condiciones
        }

// No es necesario forzar la actualización de la interfaz gráfica aquí
        txtClientePago.revalidate();
        txtClientePago.repaint();
        btnCobrar.repaint();
// Si es necesario, asegúrate de que recalcularTotales() no afecte la visibilidad de txtClientePago.
        recalcularTotales();

    }//GEN-LAST:event_comboPagoActionPerformed

    private void txtClientePagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientePagoKeyReleased
        if (txtCliente.getText().equals("VENTAS PÚBLICO EN GENERAL") && comboPago.getSelectedItem().equals("Pago a plazos.")) {
            if (!txtClientePago.getText().isEmpty()) {
                btnCobrar.setVisible(true);
            }
        } else {
            txtClientePago.setText("");
        }
    }//GEN-LAST:event_txtClientePagoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconTwo btnAgregar;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnCancelar;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnCobrar;
    private RSMaterialComponent.RSButtonMaterialIconTwo btnQuitar;
    private RSMaterialComponent.RSComboBox comboPago;
    private RSMaterialComponent.RSComboBox comboProductos;
    private newscomponents.RSDateChooser dateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCam;
    private RSMaterialComponent.RSTextFieldIconTwo labelCambio;
    private RSMaterialComponent.RSTextFieldIconTwo labelImporte;
    private javax.swing.JPanel menu;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon3;
    private RSMaterialComponent.RSTableMetro tbVentas;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtCantidad;
    private RSMaterialComponent.RSTextFieldIconTwo txtCliente;
    public static RSMaterialComponent.RSTextFieldIconTwo txtClientePago;
    private RSMaterialComponent.RSTextFieldTwo txtIdVenta;
    private RSMaterialComponent.RSTextFieldIconTwo txtIva;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtPrecio;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtStock;
    private RSMaterialComponent.RSTextFieldIconTwo txtSubtotal;
    private RSMaterialComponent.RSTextFieldIconTwo txtTotal;
    public static RSMaterialComponent.RSTextFieldIconTwo txtVendedor;
    // End of variables declaration//GEN-END:variables
}
